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

    cursor.execute('DELETE FROM Task WHERE TaskID=%d' % _TaskID)
    db.commit()

    db.close()
    return 'deleted _TaskID: %d' % _TaskID


@app.route('/registration/')
def registration():
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    UserName = request.args.get('UserName')
    Email = request.args.get('Email')
    PasswordHash = request.args.get('PasswordHash')
    CreatedAt = request.args.get('CreatedAt')
    IsActive = request.args.get('IsActive')
    cursor.execute(
        'INSERT INTO User(UserName, Email, PasswordHash, CreatedAt, ISActive) VALUES("%s", "%s", "%s", "%f", "%d")' % (UserName, Email, PasswordHash, CreatedAt, IsActive))
    db.commit()

    db.close()
    return 'UserName: %s  |  Email: %s | PasswordHash: %s | CreatedAt: %f | IsActive: %d' % (UserName, Email, PasswordHash, CreatedAt, IsActive)


@app.route('/login/')
def login():
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    Email = request.args.get('Email')
    PasswordHash = request.args.get('PasswordHash')

    cursor.execute(
        'SELECT * FROM User WHERE Email LIKE {} AND PasswordHash LIKE {}'.format(Email, PasswordHash))

    User = cursor.fetchall()
    db.commit()

    db.close()
    return User


if __name__ == '__main__':
    app.run(debug=True, threaded=True)
