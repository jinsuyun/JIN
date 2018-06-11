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

var loginSearchQuery = 'SELECT * FROM appuser WHERE id=? AND password=?'; // id/pw를 이용하여 유저 정보 search
var userDailySearchQuery = 'SELECT * FROM daily WHERE id=? AND workoutday=?';
var createDailyQuery = 'INSERT INTO daily VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)'; // daily 생성

adapter.loginSearch = function(id, password, workoutday, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) { // db연결실패
            console.log(err);
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode, []);
        } else { // db연결성공
            connection.query(loginSearchQuery, [id, password], function(err, rows1) {
                if (err) { // 로그인 실패
                    console.log(err);
                    resultCode = dbResult.Fail;
                    connection.release();
                    cb(resultCode, []);
                } else { // 로그인 성공
                    connection.query(userDailySearchQuery, [id, workoutday], function(err, rows2) {
                        if (!err) { // query가 오는 경우
                            if(!rows2[0]) { // 중복 id x
                                console.log('not duplicated id');
                                connection.query(createDailyQuery, [id, workoutday, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], function(err) {
                                    if (err) {
                                        console.log(err)
                                        resultCode = dbResult.Fail;
                                    } else {
                                        console.log("calorie success");
                                        resultCode = dbResult.OK;
                                    }
                                    connection.release();
                                    cb(resultCode, rows1);
                                });
                            } else {}
                        } else {}
                    });
                }
            });
        }
    });
}

module.exports = adapter;