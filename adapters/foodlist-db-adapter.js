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

var foodlistSearchQuery = 'SELECT * FROM food'; // foodlist 불러우기query

adapter.foodlistSearch = function(cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) { // db연결실패
            console.log(err);
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode, []);
        } else { // db연결성공
            connection.query(foodlistSearchQuery, function(err, rows) {
                if (err) { // food x
                    console.log(err);
                    resultCode = dbResult.Fail;
                    connection.release();
                    cb(resultCode, []);
                } else { // food o
                    resultCode = dbResult.OK;
                    connection.release();
                    cb(resultCode, rows);
                }
            });
        }
    });
}

module.exports = adapter;