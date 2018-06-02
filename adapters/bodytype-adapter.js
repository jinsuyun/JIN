var adapter = {};

adapter.classifyBodyType = function(obj, cb) {

    var bodytype;
    var weight = parseFloat(obj.weight);
    var height = parseFloat(obj.height);
    var BMI = (weight/((height/100)*(height/100)));

    if(BMI < 18.5) {
        type1 = "L";
        switch (obj.workperiod) {
            case 1 :
                type2 = "W";
                bodytype = type1.concat(type2);
                break;
            case 2 :
                if(obj.worklevel == "1" || obj.worklevel == "2") {
                    type2 = "W";
                } else {
                    type2 = "S";
                }
                bodytype = type1.concat(type2);
                break;
            case 3 :
                type2 = "S";
                bodytype = type1.concat(type2);
                break;
        }
    } else if(BMI >= 18.5 && BMI < 26) {
        type1 = "S";
        switch (obj.workperiod) {
            case 1 :
                if(BMI > 24.5) {
                    type2 = "F";
                } else {
                    type2 = "W";
                }
                bodytype = type1.concat(type2);
                break;
            case 2 :
                if(obj.worklevel == "1" || obj.worklevel == "2") {
                    if(BMI > 24.5) {
                        type2 = "F";
                    } else {
                        type2 = "W";
                    }
                    bodytype = type1.concat(type2);
                    break;
                } else {
                    type2 = "S";
                    bodytype = type1.concat(type2);
                    break;
                }
            case 3 :
                type2 = "S";
                bodytype = type1.concat(type2);
                break;
        }
    } else {
        type1 = "O";
        switch (obj.workperiod) {
            case 1 :
                if(BMI > 30) {
                    type2 = "F";
                } else {
                    type2 = "W";
                }
                bodytype = type1.concat(type2);
                break;
            case 2 :
                if(obj.worklevel == "1" || obj.worklevel == "2") {
                    if(BMI > 30) {
                        type2 = "F";
                    } else {
                        type2 = "B";
                    }
                    bodytype = type1.concat(type2);
                    break;
                } else {
                    bodytype = "SS";
                    break;
                }
            case 3 :
                type2 = "S";
                bodytype = type1.concat(type2);
                break;
        }
    }
    cb({"bodytype":bodytype});
}

module.exports = adapter;