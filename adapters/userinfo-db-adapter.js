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

var userSearchQuery = 'SELECT * FROM appuser WHERE id=? AND password=?'; // id/pw를 이용하여 유저 정보 search

adapter.userSearch = function(id, password) {
    pool.getConnection(function (err, connection) {
        if (!err) { // db 연결성공
            console.log("mysql connection success");
        } else { // db 연결실패
            console.error('mysql connection error');
            throw err;
            return res.json({success:false});
        }

        pool.query(userSearchQuery, [id, password], function (err, rows, fields) {
            if (!err) { // query가 오는 경우
                console.log(rows); // 삭제
                if (rows[0]) {
                    console.log('login success!'); // id/pw가 일치하여 로그인 성공
                } else {
                    console.log('login failed'); // 일치하는 id/pw가 없어 로그인 실패
                }
            } else { // query가 오지 않는 경우
                console.log(err);
            }
        });
        connection.release();
    });
}

module.exports = adapter;
