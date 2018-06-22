var adapter = {};

adapter.classifyBodyType = function(obj, cb) {

    var bodytype;
    var age = obj.age;
    var sex = obj.sex;
    var weight = parseFloat(obj.weight);
    var height = parseFloat(obj.height);
    var skeletalMuscleMass;
    var fatMass;
    var resSB;
    var resOF;
    var resOB;
    var resSW;
    var resSF;
    var resLW;
    var resLF;
    var resLB;
    var resOO;
    var bodyval;

    switch(sex) {
        case "M" :
            skeletalMuscleMass = weight * 0.95 *0.577;
            fatMass = weight * 0.2;
            if(obj.worklevel == "1") {
                skeletalMuscleMass = skeletalMuscleMass * 0.85;
                fatMass = fatMass * 1.2;
            } else if(obj.worklevel == "2") {
                skeletalMuscleMass = skeletalMuscleMass * 0.925;
                fatMass = fatMass * 1.1;
            } else if(obj.worklevel == "3") {
                skeletalMuscleMass = skeletalMuscleMass * 1;
                fatMass = fatMass * 1.0;
            } else if(obj.worklevel == "4") {
                skeletalMuscleMass = skeletalMuscleMass * 1.075;
                fatMass = fatMass * 0.9;
            } else {
                skeletalMuscleMass = skeletalMuscleMass * 1.15;
                fatMass = fatMass * 0.8;
            }

            resLW = Math.abs(age * 4.9581 + skeletalMuscleMass * (-116.0731) + fatMass * (-230.151)
                + height * 13.3547 + weight * (-51.663) - 14737.6424);
            resLF = Math.abs(age * (-21.0959) + skeletalMuscleMass * 10.4733 + fatMass * 121.8021
                + height * 9.4354 + weight * (-65.7412) - 15110.7008);
            resSW = Math.abs(age * 3.7604 + skeletalMuscleMass * (-99.9495) + fatMass * (-222.3107)
                + height * 0.7389 + weight * 136.596 - 3499.1217);
            resSB = Math.abs(age * 3.6841 + skeletalMuscleMass * (-75.7988) + fatMass * (-227.4724)
                + height * (-0.647) + weight * 141.9386 - 2125.5622);
            resSF = Math.abs(age * 3.6487 + skeletalMuscleMass * (-44.8042) + fatMass * 484.362
                + height * (-14.8571) + weight * 117.8766 + 12262.0757);
            resOB = Math.abs(age * 3.4298 + skeletalMuscleMass * (-74.0036) + fatMass * (-226.0526)
                + height * (-4.6142) + weight * 186.8017 + 1304.5373);
            resOF = Math.abs(age * 3.7391 + skeletalMuscleMass * (-27.6777) + fatMass * 495.9379
                + height * (-19.0337) + weight * 157.9808 + 15729.4355);

            bodyval = Math.min(resLW, resLF, resSW, resSB, resSF, resOB, resOF);

            if(bodyval == resLW) {
                if(obj.worklevel == "4" || obj.worklevel == "5")
                    bodytype = "LB";
                else
                    bodytype = "LW";
            } else if(bodyval == resLF){
                if(obj.worklevel == "4" || obj.worklevel == "5")
                    bodytype = "LB";
                else
                    bodytype = "LF";
            } else if(bodyval == resSW) {
                bodytype = "SW";
            } else if(bodyval == resSB) {
                if(obj.worklevel == "4" || obj.worklevel == "5")
                    bodytype = "SS";
                else
                    bodytype = "SB";
            } else if(bodyval == resSF) {
                bodytype = "SF";
            } else if(bodyval == resOB) {
                if(obj.worklevel == "4" || obj.worklevel == "5")
                    bodytype = "OO";
                else
                    bodytype = "OB";
            } else {
                bodytype = "OF";
            }
            break;
        case "W" :
            skeletalMuscleMass = weight * 0.95 *0.45;
            fatMass = weight * 0.25;
            if(obj.worklevel == "1") {
                skeletalMuscleMass = skeletalMuscleMass * 0.9;
                fatMass = fatMass * 1.2;
            } else if(obj.worklevel == "2") {
                skeletalMuscleMass = skeletalMuscleMass * 0.95;
                fatMass = fatMass * 1.1;
            } else if(obj.worklevel == "3") {
                skeletalMuscleMass = skeletalMuscleMass * 1;
                fatMass = fatMass * 1.0;
            } else if(obj.worklevel == "4") {
                skeletalMuscleMass = skeletalMuscleMass * 1.05;
                fatMass = fatMass * 0.9;
            } else {
                skeletalMuscleMass = skeletalMuscleMass * 1.1;
                fatMass = fatMass * 0.8;
            }

            resLW = Math.abs(age * (-29.7922) + skeletalMuscleMass * 3.929 + fatMass * 19.6821
                + height * 19.8604 + weight * (-383.5817) - 11235.4521);
            resLB = Math.abs(age * (-29.7162) + skeletalMuscleMass * 22.0166 + fatMass * 14.673
                + height * 19.0233 + weight * (-379.3962) - 10398.4952);
            resSW = Math.abs(age * (-29.8121) + skeletalMuscleMass * 9.6286 + fatMass * 18.2885
                + height * 0.8368 + weight * (-60.0554) + 33795.6912);
            resSB = Math.abs(age * (-29.7958) + skeletalMuscleMass * 15.7797 + fatMass * 18.1209
                + height * 0.6103 + weight * (-59.717) + 4022.241);
            resSF = Math.abs(age * (-29.8085) + skeletalMuscleMass * 9.5436 + fatMass * 31.6904
                + height * 0.1545 + weight * (-56.1584) + 4468.9371);
            resOF = Math.abs(age * (-29.9718) + skeletalMuscleMass * (-5.2769) + fatMass * 22.3798
                + height * (-4.5586) + weight * 12.202 + 8178.1531);
            resOO = Math.abs(age * (-30.5635) + skeletalMuscleMass * (-0.7333) + fatMass * 20.1546
                + height * (-4.553) + weight * 9.3823 - 8284.4495);

            bodyval = Math.min(resLW, resLB, resSW, resSB, resSF, resOF, resOO);

            if(bodyval == resLW) {
                bodytype = "LW";
            } else if(bodyval == resLB) {
                if(obj.worklevel == "1" || obj.worklevel == "2")
                    bodytype = "LF";
                else
                    bodytype = "LB";
            } else if(bodyval == resSW) {
                bodytype = "SW"
            } else if(bodyval == resSB) {
                if(obj.worklevel == "4" || obj.worklevel == "5")
                    bodytype = "SS";
                else
                    bodytype = "SB";
            } else if(bodyval == resSF) {
                bodytype = "SF";
            } else if(bodyval == resOF) {
                if(obj.worklevel == "4" || obj.worklevel == "5")
                    bodytype = "OB";
                else
                    bodytype = "OF";

            } else {
                bodytype = "OO";
            }
            break;
    }
    cb({"bodytype":bodytype});
}

module.exports = adapter;