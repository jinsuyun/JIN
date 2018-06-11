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

var userSearchQuery = 'SELECT * FROM daily WHERE id=?'; // id/pw를 이용하여 유저 정보 search

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