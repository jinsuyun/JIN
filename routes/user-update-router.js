var express = require('express');
var router = express.Router();
var userUpdateAdapter = require('../adapters/userupdate-db-adapter');
var bodyTypeAdapter = require('../adapters/bodytype-adapter');
var dbConnection = require('./result');

var userId;
var userWeight;
var userHeight;
var userTargetWeight;
var userTargetPeriod;
var userWorkPeriod;
var userWorkLevel;
var userBodyType;

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    console.log(obj);
    userId = obj.id;
    userWeight = obj.weight;
    userHeight = obj.height;
    userTargetWeight = obj.targetweight;
    userTargetPeriod = obj.targetperiod;
    userWorkPeriod = obj.workperiod;
    userWorkLevel = obj.worklevel;

    if(userId == '' || userWeight == ''  || userHeight == '' || userTargetWeight == '' || userTargetPeriod == '' || userWorkPeriod == '' || userWorkLevel == '') {
        return res.json({success:false});
    } else {

    }

    bodyTypeAdapter.classifyBodyType(obj, function (rows) {
        userBodyType = rows.bodytype;
        obj = Object.assign(obj, rows);
    })

    userUpdateAdapter.write(obj, function(resultCode, rows){ // obj에 bodytype추가 수정
        if(resultCode == dbConnection.OK){
            userUpdateAdapter.search(obj, function(resultCode, rows){
                if(resultCode == dbConnection.OK) {
                    var response;
                    response = Object.assign(rows[0], {"success":true});
                    res.json(response);
                }
            })
        }
        else {
            console.log("false reason: query false");
            res.json({"success": false});
        }
    });
});

module.exports = router;
