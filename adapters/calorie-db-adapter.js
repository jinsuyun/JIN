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

var userDataSearchQuery = 'SELECT * FROM daily WHERE id=? AND workoutday=?';
var calorieNewWriteQuery = 'INSERT INTO daily(eat_calories, id, workoutday) VALUE (?,?,?)'; // id 없는경우 새로등록
var calorieWriteQuery = 'UPDATE daily SET eat_calories=? WHERE id=? AND workoutday=?'; // id 있는경우 update

adapter.calorieWrite = function(user, cb) {
    var resultCode = dbResult.Fail;

    pool.getConnection(function(err, connection) {
        if (err) {
            console.log(err)
            resultCode = dbResult.Fail;
            connection.release();
            cb(resultCode);
        } else {
            connection.query(userDataSearchQuery, [user.id, user.workoutday], function(err, rows) {
                if (!err) { // query가 오는 경우
                    console.log(rows);
                    if(!rows[0]) { // 중복 id x
                        console.log('duplicated id');
                        connection.query(calorieNewWriteQuery, [user.eat_calories, user.id, user.workoutday], function(err) {
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
                    } else {
                        connection.query(calorieWriteQuery, [user.eat_calories, user.id, user.workoutday], function(err) {
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
                    }
                } else { // query가 오지 않는 경우
                    console.log(err);
                }
            });
        }
    });
}

module.exports = adapter;
