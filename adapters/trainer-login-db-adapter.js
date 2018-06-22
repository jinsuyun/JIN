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

var loginSearchQuery = 'SELECT * FROM trainer WHERE id=? AND password=?'; // id/pw를 이용하여 유저 정보 search

adapter.loginSearch = function(id, password, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) { // db연결실패
            console.log(err);
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode, []);
        } else { // db연결성공
            connection.query(loginSearchQuery, [id, password], function(err, rows) {
                if (err) { // 로그인 실패
                    console.log(err);
                    resultCode = dbResult.Fail;
                    connection.release();
                    cb(resultCode, []);
                } else { // 로그인 성공
                    resultCode = dbResult.OK;
                    connection.release();
                    cb(resultCode, rows);
                }
            });
        }
    });
}

module.exports = adapter;
