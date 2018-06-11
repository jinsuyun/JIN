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
                    connection.query(userDailySearchQuery, [user.id, user.workoutday], function(err, rows) {
                        if (!err) { // query가 오는 경우
                            console.log(rows);
                            if(!rows[0]) { // 중복 id x
                                console.log('not duplicated id');
                                connection.query(createDailyQuery, [user.id, user.workoutday, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], function(err) {
                                    if (err) {
                                        console.log(err)
                                        resultCode = dbResult.Fail;
                                        connection.release();
                                        cb(resultCode);
                                    } else {
                                        console.log("calorie success");
                                        resultCode = dbResult.OK;
                                        connection.release();
                                        cb(resultCode);
                                    }
                                });
                            } else {}
                        } else {
                        }
                    });
                    resultCode = dbResult.OK;
                    connection.release();
                    cb(resultCode, rows);
                }
            });
        }
    });
}

module.exports = adapter;