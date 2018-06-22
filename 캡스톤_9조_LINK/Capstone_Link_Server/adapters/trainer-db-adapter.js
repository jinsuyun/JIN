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

var trainerSearchQuery = 'SELECT * FROM trainer';

adapter.trainerSearch = function(cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) { // db연결실패
            console.log(err);
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode, []);
        } else { // db연결성공
            connection.query(trainerSearchQuery, function(err, rows) {
                if (err) {
                    console.log(err);
                    resultCode = dbResult.Fail;
                    connection.release();
                    cb(resultCode, []);
                } else {
                    resultCode = dbResult.OK;
                    connection.release();
                    cb(resultCode, rows);
                }
            });
        }
    });
}

module.exports = adapter;
