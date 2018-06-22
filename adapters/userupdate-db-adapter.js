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
var userInfoWriteQuery = 'UPDATE appuser SET weight=?, height=?, targetweight=?, targetperiod=?,' +
    ' workperiod=?, worklevel=?, bodytype=? WHERE id=?'; // 유저 정보입력 query, 이 외의 추가적인 정보 더 입력

adapter.write = function(user, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) {
            console.log(err)
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode);
        } else {
            connection.query(userIdSearchQuery, [user.id], function(err, rows) {
                if (!err) { // query가 오는 경우
                    if(!rows[0]) { // 중복 id 존재x
                        console.log('not duplicated id');
                        resultCode = dbResult.Fail;
                        connection.release();
                        cb(resultCode, []);
                    } else { // 중복 id 존재o
                        connection.query(userInfoWriteQuery, [user.weight, user.height,
                            user.targetweight, user.targetperiod, user.workperiod, user.worklevel,
                            user.bodytype, user.id], function(err) {
                            if (err) {
                                console.log(err)
                                resultCode = dbResult.Fail;
                                connection.release();
                                cb(resultCode, []);
                            } else {
                                console.log("input success");
                                resultCode = dbResult.OK;
                                connection.release();
                                cb(resultCode, rows);
                            }
                        });
                    }
                } else { // query가 오지 않는 경우
                    console.log(err);
                }
            });
        }
    });
}

adapter.search = function(user, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) {
            console.log(err)
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode);
        } else {
            connection.query(userIdSearchQuery, [user.id], function(err, rows) {
                if (!err) { // query가 오는 경우
                    if(!rows[0]) { // 중복 id 존재x
                        console.log('not duplicated id');
                        resultCode = dbResult.Fail;
                        connection.release();
                        cb(resultCode, []);
                    } else { // 중복 id 존재o
                        console.log("input success");
                        resultCode = dbResult.OK;
                        connection.release();
                        cb(resultCode, rows);
                    }
                } else { // query가 오지 않는 경우
                    console.log(err);
                }
            });
        }
    });
}

module.exports = adapter;
