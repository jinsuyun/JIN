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

var userSearchQuery = 'SELECT * FROM appuser WHERE id=?'; // id/pw를 이용하여 유저 정보 search

adapter.appuserSearch = function(id, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) { // db연결실패
            console.log(err);
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode, []);
        } else { // db연결성공
            connection.query(userSearchQuery, [id], function(err, rows) {
                if (!rows[0]) { // appuser x
                    console.log(err);
                    resultCode = dbResult.Fail;
                    connection.release();
                    cb(resultCode, Object.assign([], {"success":false}));
                } else { // daily o
                    resultCode = dbResult.OK;
                    connection.release();
                    cb(resultCode, Object.assign(rows, {"success":true}));
                }
            });
        }
    });
}

module.exports = adapter;