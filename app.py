from flask import Flask
from flask import request
import sqlite3

app = Flask(__name__)


@app.route('/')
def root():
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    cursor.execute('SELECT * FROM Task')
    data = cursor.fetchall()

    db.close()

    return str(data)


@app.route('/create')
def create():
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    TaskTitle = request.args.get('TaskTitle')
    StartAt = request.args.get('StartAt')
    EndAt = request.args.get('EndAt')
    Dated = request.args.get('Dated')
    IsWholeDay = request.args.get('IsWholeDay')
    IsCompleted = request.args('IsCompleted')
    CompletedAt = request.args('CompletedAt')
    Description = request.args('Description')

    cursor.execute(
        'INSERT INTO Task(TaskTitle, StartAt, EndAt, Dated, IsWholeDay, IsCompleted, CompletedAt, Description) VALUES("%s", "%s", "%s", "%f", "%d")' % (TaskTitle, StartAt, EndAt, Dated, IsWholeDay, IsCompleted, CompletedAt, Description))
    db.commit()

    db.close()
    return 'TaskTitle: %s | StartAt: %f | EndAt: %f | Dated: %f | IsWholeDay: %d | IsCompleted: %d | CompletedAt: %f | Description: %s' % (TaskTitle, StartAt, EndAt, Dated, IsWholeDay, IsCompleted, CompletedAt, Description)


@app.route('/update/<_TaskID>')
def update(_TaskID):
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    TaskTitle = request.args.get('TaskTitle')
    StartAt = request.args.get('StartAt')
    EndAt = request.args.get('EndAt')
    Dated = request.args.get('Dated')
    IsWholeDay = request.args.get('IsWholeDay')
    IsCompleted = request.args('IsCompleted')
    CompletedAt = request.args('CompletedAt')
    Description = request.args('Description')

    cursor.execute(
        'UPDATE Task SET TaskTitle="%s", StartAt="%f", EndAt="%f", Dated="%f", IsWholeDay="%d", IsCompleted="%d", CompletedAt="%f", Description="%s" WHERE TaskID= %d' % (TaskTitle, StartAt, EndAt, Dated, IsWholeDay, IsCompleted, CompletedAt, Description, _TaskID))
    db.commit()

    db.close()
    return '_TaskID: %d  | TaskTitle: %s | StartAt: %f | EndAt: %f | Dated: %f | IsWholeDay: %d | IsCompleted: %d | CompletedAt: %f | Description: %s' % (_TaskID, TaskTitle, StartAt, EndAt, Dated, IsWholeDay, IsCompleted, CompletedAt, Description)


@app.route('/delete/<_TaskID>')
def delete(_TaskID):
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    cursor.execute('DELETE FROM Task WHERE TaskID=%d' % _UserID)
    db.commit()

    db.close()
    return 'deleted _TaskID: %d' % _TaskID


if __name__ == '__main__':
    app.run(debug=True, threaded=True)
