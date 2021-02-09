from flask import Flask
from flask import request
import sqlite3

app = Flask(__name__)


@app.route('/')
def root():
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    cursor.execute('SELECT * FROM todosqli')
    data = cursor.fetchall()

    db.close()

    return str(data)


@app.route('/create')
def create():
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    UserName = request.args.get('UserName')
    Email = request.args.get('Email')
    PasswordHash = request.args.get('PasswordHash')
    CreatedAt = request.args.get('CreatedAt')
    IsActive = request.args.get('IsActive')
    cursor.execute(
        'INSERT INTO todosqli(UserName, Email, PasswordHash, CreatedAt, ISActive) VALUES("%s", "%s", "%s", "%f", "%d")' % (UserName, Email, PasswordHash, CreatedAt, IsActive))
    db.commit()

    db.close()
    return 'UserName: %s  |  Email: %s | PasswordHash: %s | CreatedAt: %f | IsActive: %d' % (UserName, Email, PasswordHash, CreatedAt, IsActive)


@app.route('/update/<_UserID>')
def update(_UsedID):
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    UserName = request.args.get('UserName')
    Email = request.args.get('Email')
    PasswordHash = request.args.get('PasswordHash')
    CreatedAt = request.args.get('CreatedAt')
    IsActive = request.args.get('IsActive')

    cursor.execute(
        'UPDATE todosqli SET UserName= "%s", Email="%s", PasswordHash="%s", CreatedAt="%f", IsActive="%d" WHERE UserID= %d' % (UserName, Email, PasswordHash, CreatedAt, IsActive, _UserID))
    db.commit()

    db.close()
    return '_UserID: %d  |  UserName: %s  |  Email: %s | PasswordHash: %s | CreatedAt: %f | IsActive: %d' % (_UserID, UserName, Email, PasswordHash, CreatedAt, IsActive)


@app.route('/delete/<_UserID>')
def delete(_UserID):
    db = sqlite3.connect('todosqli.db')
    cursor = db.cursor()

    cursor.execute('DELETE FROM todosqli WHERE UserID=%d' % _UserID)
    db.commit()

    db.close()
    return 'deleted _UserID: %d' % _UserID


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
