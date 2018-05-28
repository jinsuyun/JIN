var express = require('express');
var router = express.Router();
var userInputAdapter =require('../adapters/userinput-db-adapter');
var dbConnection = require('./result');

var userId;
var userSex;
var userAge;
var userWeight;
var userHeight;
var userTargetWeight;
var userTargetPeriod;
var userWorkLevel;

router.post('/', function(req, res) {

    var str = Object.keys(req.body);
    var obj = JSON.parse(str[0]);
    userId = obj.id;
    userSex = obj.sex;
    userAge = obj.age;
    userWeight = obj.weight;
    userHeight = obj.height;
    userTargetWeight = obj.targetweight;
    userTargetPeriod = obj.targetperiod;
    userWorkPeriod = obj.workperiod;
    userWorkLevel = obj.worklevel;

    if(userId == '' || userSex == '' || userAge == '' || userWeight == ''  || userHeight == ''
        || userTargetWeight == '' || userTargetPeriod == '' || userWorkPeriod == '' || userWorkLevel == '') {
        return res.json({success:false});
    } else {

    }

    userInputAdapter.write(obj, function(resultCode, rows){
        if(resultCode == dbConnection.OK){
            userInputAdapter.search(obj, function(resultCode, rows){
                if(resultCode == dbConnection.OK) {
                    var response;
                    console.log(rows);
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
