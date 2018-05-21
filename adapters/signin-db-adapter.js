var mysql = require('mysql');
var dbConfig = {
    host: '18.221.204.247',
    port: '3306',
    user: 'root', // mysql user
    password: '5907', // mysql password
    database: 'capstone',
    connectionLimit: 10
}
var pool = mysql.createPool(dbConfig);
var adapter = {};

var userIdSearchQuery = 'SELECT * FROM appuser WHERE id=?'; // id를 이용하여 유저 정보 search
var userWriteQuery = 'INSERT INTO appuser(id, password, name, email) VALUE (?,?,?,?)'; // 유저 회원가입 query

adapter.signinWrite = function(body) {
    pool.getConnection(function (err, connection) {
        if (!err) {
            console.log("mysql connection success");
        } else {
            console.error('mysql connection error');
            console.error(err);
            throw err;
        }

        pool.query(userIdSearchQuery, body.id, function(err, rows, fields) {
            if (!err) { // query가 오는 경우
                console.log(rows);
                if(rows[0]) { // 중복 id 존재
                    console.log('중복된 id입니다.');
                } else {
                    pool.query(userWriteQuery, [body.id, body.password, body.name, body.emailAddress], function (err, rows, fields) {
                        if (!err) { // query가 오는 경우
                            console.log('회원가입 성공');
                        } else { // query가 오지 않는 경우
                            console.log(err);
                        }
                    });
                }
            } else { // query가 오지 않는 경우
                console.log(err);
            }
        })
        connection.release();
    });
}

module.exports = adapter;
