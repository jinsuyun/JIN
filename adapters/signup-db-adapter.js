var mysql = require('mysql');
var dbConfig = {
    host: '18.221.204.247',
    port: '3306',
    user: 'root', // mysql user
    password: '5907', // mysql password
    database: 'capstone',
    connectionLimit: 10
}
var dbResult = require('../routes/result');
var pool = mysql.createPool(dbConfig);
var adapter = {};


var userIdSearchQuery = 'SELECT * FROM appuser WHERE id=?'; // id를 이용하여 유저 정보 search
var userWriteQuery = 'INSERT INTO appuser(id, password, name, email) VALUE (?,?,?,?)'; // 유저 회원가입 query

adapter.signupWrite = function(user, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) {
            console.log(err)
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode);
        } else {
            pool.query(userIdSearchQuery, user.id, function(err, rows) {
                if (!err) { // query가 오는 경우
                    console.log(rows);
                    if(rows[0]) { // 중복 id 존재
                        console.log('중복된 id입니다.');
                        resultCode = dbResult.Fail;
                        connection.release();
                        cb(resultCode);
                    } else {
                        connection.query(userWriteQuery, user.id, user.password, user.name, user.name, function(err) {
                            if (err) {
                                console.log(err)
                                resultCode = dbResult.Fail;
                                connection.release();
                                cb(resultCode);
                            } else {
                                resultCode = dbResult.OK;
                                connection.release();
                                cb(resultCode);
                            }
                        });
                    }
                } else { // query가 오지 않는 경우
                    console.log(err);
                }
            })
        }
    });
}

module.exports = adapter;
