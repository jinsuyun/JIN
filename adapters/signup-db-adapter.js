var bcrypt = require('bcrypt-nodejs');
var mysql = require('mysql');
var db_config = require('../db-config');
var dbConfig = {
    host: db_config.host,
    port: db_config.port,
    user: db_config.user,
    password: db_config.password,
    database: db_config.database,
    connectionLimit: db_config.connectionLimit
}
var dbResult = require('../routes/result');
var pool = mysql.createPool(dbConfig);
var adapter = {};

var userIdSearchQuery = 'SELECT * FROM appuser WHERE id=?'; // id를 이용하여 유저 정보 search
var userWriteQuery = 'INSERT INTO appuser(id, password, name, email) VALUE (?,?,?,?)'; // 유저 회원가입 query

adapter.signupWrite = function(user, cb) {
    var resultCode = dbResult.Fail;
    var password = user.password;
    console.log(user);
    pool.getConnection(function(err, connection) {
        if (err) {
            console.log(err)
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode);
        } else {
            connection.query(userIdSearchQuery, user.id, function(err, rows) {
                if (!err) { // query가 오는 경우
                    console.log(rows);
                    if(rows[0]) { // 중복 id 존재
                        console.log('duplicated id');
                        resultCode = dbResult.Fail;
                        connection.release();
                        cb(resultCode);
                    } else {
                        bcrypt.hash(password, null, null, function(err, hash) {
                            password = hash;
                            connection.query(userWriteQuery, [user.id, password, user.name, user.email], function(err) {
                                if (err) {
                                    console.log(err)
                                    resultCode = dbResult.Fail;
                                    connection.release();
                                    cb(resultCode);
                                } else {
                                    console.log("signup success");
                                    resultCode = dbResult.OK;
                                    connection.release();
                                    cb(resultCode);
                                }
                            });
                        });
                    }
                } else { // query가 오지 않는 경우
                    console.log(err);
                }
            });
        }
    });
}

module.exports = adapter;
