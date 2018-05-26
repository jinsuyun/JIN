$(document).ready(function () {
    var myapi = "http://localhost:3000/appuserjson"
    var appuser_name = [];
    var appuser_id = [];
    var bmi = [];
    $.getJSON(myapi, function (info) {
        $.each(info, function (key, item) {
            appuser_name[key] = item["name"];
            appuser_id[key] = item["id"];
            $(".dropdown-menu").append("<li value=" + appuser_id[key] + "><a>" + appuser_name[key] + "</a></li>");
            makeWeekSelectOptions();
        });
    });

});
var start_date=[];
var end_date=[];
$(document).ready(function () {
    $('.dropdown-menu').on("click", "li", function (req, res) {
        makeWeekSelectOptions();
        //console.log(start_date,end_date);
        var appuserapi = "http://localhost:3000/appuserjson"
        var dailyapi = "http://localhost:3000/dailyjson"
        var id = $(this).attr('value');
        var real_date;
       
        
        var match_date = 0;
          
       $.getJSON("http://localhost:3000/dailyjson", function (info) {
            $.each(info, function (key, item) {
                real_date = new Date(info[key]["workoutday"]);
                /*if (start_year < real_year && end_year > real_year)
                    match_year = 1;
               */
            });

        });
        
        $.getJSON(appuserapi, function (info) {
            var appuser = [];
            var bmi = [];
            $.each(info, function (key, item) {
                if (id == info[key]['id']) {
                    bmi[key] = item.weight / ((item.height / 100) * (item.height / 100));
                    bmi[key] = bmi[key].toFixed(2);
                    var bmi_chart = AmCharts.makeChart("bmi", {
                        "type": "serial",
                        "rotate": true,
                        "theme": "light",
                        "autoMargins": false,
                        "marginTop": 30,
                        "marginLeft": 80,
                        "marginBottom": 30,
                        "marginRight": 50,
                        "titles": [{
                            "text": "B M I"
                    }],
                        "legend": {
                            "horizontalGap": 1,
                            "maxColumns": 1,
                            "position": "right",
                            "useGraphSettings": true,
                            "markerSize": 10
                        },
                        "dataProvider": [{
                            "category": "BMI",
                            "low_weight": 18.5,
                            "normal": 4.5,
                            "over_weight": 2,
                            "obsecity": 5,
                            "high_obsecity": 5,
                            "full": 35,
                            "bullet": bmi[key],
                    }],
                        "valueAxes": [{
                            "minimum": 14,
                            "maximum": 35,
                            "stackType": "regular",
                            "gridAlpha": 0
                    }],
                        "startDuration": 1,
                        "graphs": [{
                            "fillAlphas": 0.8,
                            "lineColor": "#19d228",
                            "showBalloon": false,
                            "type": "column",
                            "valueField": "low_weight",
                            "title": "저체중"
                        }, {
                            "fillAlphas": 0.8,
                            "lineColor": "#f4fb16",
                            "showBalloon": false,
                            "type": "column",
                            "valueField": "normal",
                            "title": "정상"
                        }, {
                            "fillAlphas": 0.8,
                            "lineColor": "#f6d32b",
                            "showBalloon": false,
                            "type": "column",
                            "valueField": "over_weight",
                            "title": "과체중"
                        }, {
                            "fillAlphas": 0.8,
                            "lineColor": "#fb7116",
                            "showBalloon": false,
                            "type": "column",
                            "valueField": "obsecity",
                            "title": "비만"
                        }, {
                            "fillAlphas": 0.8,
                            "lineColor": "#ff0000",
                            "showBalloon": false,
                            "type": "column",
                            "valueField": "high_obsecity",
                            "title": "고도 비만"
                        }, {
                            "clustered": false,
                            "columnWidth": 0.3,
                            "fillAlphas": 1,
                            "lineColor": "#0027ff",
                            "stackable": false,
                            "type": "column",
                            "valueField": "bullet",
                            "title": "자신의 BMI"
                        }],
                        "columnWidth": 1,
                        "categoryField": "category",
                        "categoryAxis": {
                            "gridAlpha": 0,
                            "position": "left"
                        }
                    });
                }
            });

        });
        $.getJSON(dailyapi, function (info) {
            var set = [];
            var set_day = []; //주마다 세트수 
            var set_date = []; //요일마다 세트수
            var set_arm = 0,
                set_sixpack = 0,
                set_leg = 0,
                set_back = 0,
                set_chest = 0,
                set_shoulder = 0;
            $.each(info, function (key, item) {
                if (id == info[key]['id']) {
                    set_arm += info[key]["arm"];
                    set_sixpack += info[key]["sixpack"];
                    set_leg += info[key]["leg"];
                    set_back += info[key]["back"];
                    set_chest += info[key]["chest"];
                    set_shoulder += info[key]["shoulder"];
                }
            });
            $.each(info, function (key, item) {

                if (id == info[key]['id']) {
                    var part_chart = AmCharts.makeChart("part", {
                        "type": "radar",
                        "theme": "light",
                        "addClassNames": true,
                        "fontSize": 13,
                        "titles": [{
                            "text": "부위별 세트"
                    }],
                        "dataProvider": [{
                            "country": "팔",
                            "litres": set_arm,
                            "color": "#67b7dc"
                        }, {
                            "country": "하체",
                            "litres": set_leg,
                            "color": "#fdd400"
                        }, {
                            "country": "등",
                            "litres": set_back,
                            "color": "#84b761"
                        }, {
                            "country": "가슴",
                            "litres": set_chest,
                            "color": "#cc4748"
                        }, {
                            "country": "어깨",
                            "litres": set_shoulder,
                            "color": "#cd82ad"
                        }, {
                            "country": "복근",
                            "litres": set_sixpack,
                            "color": "#2f4074"
                        }],
                        "valueAxes": [{
                            "axisTitleOffset": 20,
                            "minimum": 0,
                            "axisAlpha": 0.15
                    }],
                        "startDuration": 2,
                        "graphs": [{
                            "balloonText": "[[value]] 세트",
                            "bullet": "round",
                            "lineThickness": 2,
                            "valueField": "litres"
                    }],
                        "categoryField": "country",
                        "listeners": [{
                            "event": "rendered",
                            "method": updateLabels
                    }, {
                            "event": "resized",
                            "method": updateLabels
                    }]
                    });
                }
            });
            var day = []; //일
            var date = []; //요일
            var run_mon, run_tue, run_wed, run_thu, run_fri, run_sat, run_sun;
            var weight_mon, weight_tue, weight_wed, weight_thu, weight_fri, weight_sat, weight_sun;
            var sum_week_run = [];
            var sum_week_weight = [];

            $.each(info, function (key, item) {

                if (id == info[key]['id']) {
                    day[key] = (info[key]['workoutday']);
                    date[key] = new Date(day[key]).getDay();

                    if (date[key] == 0) {
                        run_sun = info[key]["running_time"];
                        weight_sun = info[key]["weight_time"];
                    } else if (date[key] == 1) {
                        run_mon = info[key]["running_time"];
                        weight_mon = info[key]["weight_time"];
                    } else if (date[key] == 2) {
                        run_tue = info[key]["running_time"];
                        weight_tue = info[key]["weight_time"];
                    } else if (date[key] == 3) {
                        run_wed = info[key]["running_time"];
                        weight_wed = info[key]["weight_time"];
                    } else if (date[key] == 4) {
                        run_thu = info[key]["running_time"];
                        weight_thu = info[key]["weight_time"];
                    } else if (date[key] == 5) {
                        run_fri = info[key]["running_time"];
                        weight_fri = info[key]["weight_time"];
                    } else if (date[key] == 6) {
                        run_sat = info[key]["running_time"];
                        weight_sat = info[key]["weight_time"];
                    }
                    sum_week_run = (run_mon + run_tue + run_wed + run_thu + run_fri + run_sat + run_sun) / 60;
                    sum_week_weight = (weight_mon + weight_tue + weight_wed + weight_thu + weight_fri + weight_sat + weight_sun) / 60;
                    sum_week_run = sum_week_run.toFixed(2);
                    sum_week_weight = sum_week_weight.toFixed(2);
                }
            });
            $.each(info, function (key, item) {
                if (id == info[key]['id']) {
                    var month_chart = AmCharts.makeChart("month", {
                        "type": "serial",
                        "theme": "light",
                        "titles": [{
                            "text": "월간 운동량"
                        }],
                        "dataProvider": [{
                            "country": "1주차",
                            "running": sum_week_run,
                            "weight": sum_week_weight
                            }, {
                            "country": "2주차",
                            "running": sum_week_run,
                            "weight": sum_week_weight
                            }, {
                            "country": "3주차",
                            "running": sum_week_run,
                            "weight": sum_week_weight,
                            }, {
                            "country": "4주차",
                            "running": sum_week_run,
                            "weight": sum_week_weight,
                            }],
                        "graphs": [{
                            "fillAlphas": 0.9,
                            "lineAlpha": 0.2,
                            "type": "column",
                            "valueField": "running",
                            "balloonText": "유산소:[[value]]시간",
                            }, {
                            "fillAlphas": 0.9,
                            "lineAlpha": 0.2,
                            "type": "column",
                            "valueField": "weight",
                            "balloonText": "웨이트:[[value]]시간",
                        }],
                        "categoryField": "country",
                        "chartCursor": {
                            "fullWidth": true,
                            "cursorAlpha": 0.1,
                            "listeners": [{
                                "event": "changed",
                                "method": function (ev) {
                                    // Log last cursor position
                                    ev.chart.lastCursorPosition = ev.index;
                                }
                            }]
                        }
                    });
                }
            });

            $.each(info, function (key, item) {
                var week_chart = AmCharts.makeChart("week", {
                    "type": "serial",
                    "theme": "light",
                    "legend": {
                        "horizontalGap": 10,
                        "maxColumns": 1,
                        "position": "right",
                        "markerSize": 10
                    },
                    "titles": [{
                        "text": "주간 운동량"
                        }],
                    "dataProvider": [{
                        "country": "월",
                        "running": run_mon,
                        "weight": weight_mon,
                            }, {
                        "country": "화",
                        "running": run_tue,
                        "weight": weight_tue,
                            }, {
                        "country": "수",
                        "running": run_wed,
                        "weight": weight_wed,
                            }, {
                        "country": "목",
                        "running": run_thu,
                        "weight": weight_thu,
                            }, {
                        "country": "금",
                        "running": run_fri,
                        "weight": weight_fri,
                            }, {
                        "country": "토",
                        "running": run_sat,
                        "weight": weight_sat,
                            }, {
                        "country": "일",
                        "running": run_sun,
                        "weight": weight_sun,
                            }],
                    "graphs": [{
                        "balloonText": "유산소:[[value]]분",
                        "fillAlphas": 0.9,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "title": "유산소",
                        "valueField": "running"
                            }, {
                        "balloonText": "웨이트:[[value]]분",
                        "fillAlphas": 0.9,
                        "lineAlpha": 0.2,
                        "type": "column",
                        "title": "무산소",
                        "valueField": "weight"
                        }],
                    "categoryField": "country",
                    "chartCursor": {
                        "fullWidth": true,
                        "cursorAlpha": 0.1,
                        "listeners": [{
                            "event": "changed",
                            "method": function (ev) {
                                // Log last cursor position
                                ev.chart.lastCursorPosition = ev.index;
                            }
                        }]
                    }
                });
                //ㅇㅇㅇㅇㅇㅇ수정시작
                $.each(info, function (key, item) {
                    var workout_calories_chart = AmCharts.makeChart("workout_calories", {
                        "type": "pie",
                        "theme": "none",
                        "innerRadius": "40%",
                        "gradientRatio": [-0.4, -0.4, -0.4, -0.4, -0.4, -0.4, 0, 0.1, 0.2, 0.1, 0, -0.2, -0.5],
                        "titles": [{
                            "text": "소모 칼로리량"
                    }],
                        "dataProvider": [{
                            "country": "소모한 칼로리",
                            "litres": info[key]["spent_calories"]
                    }, {
                            "country": "소모해야할 칼로리",
                            "litres": info[key]["all_spent_calories"] - info[key]["spent_calories"]
                    }],
                        "balloonText": "[[value]]",
                        "valueField": "litres",
                        "titleField": "country",
                        "balloon": {
                            "drop": true,
                            "adjustBorderColor": false,
                            "color": "#FFFFFF",
                            "fontSize": 16
                        },
                        "export": {
                            "enabled": true
                        }
                    });
                });

                $.each(info, function (key, item) {
                    var eat_calories_chart = AmCharts.makeChart("eat_calories", {
                        "type": "pie",
                        "theme": "none",
                        "innerRadius": "40%",
                        "gradientRatio": [-0.4, -0.4, -0.4, -0.4, -0.4, -0.4, 0, 0.1, 0.2, 0.1, 0, -0.2, -0.5],
                        "titles": [{
                            "text": "섭취 칼로리량"
                    }],
                        "dataProvider": [{
                            "country": "섭취한 칼로리",
                            "litres": info[key]["eat_calories"]
                        }, {
                            "country": "섭취해야할 칼로리",
                            "litres": info[key]["all_eat_calories"] - info[key]["eat_calories"]
                        }],
                        "balloonText": "[[value]]",
                        "valueField": "litres",
                        "titleField": "country",
                        "balloon": {
                            "drop": true,
                            "adjustBorderColor": false,
                            "color": "#FFFFFF",
                            "fontSize": 16
                        },
                        "export": {
                            "enabled": true
                        }
                    });
                });
            });

            $.each(info, function (key, item) {
                var weight_chart = AmCharts.makeChart("weight", {
                    "type": "serial",
                    "theme": "light",
                    "marginRight": 40,
                    "marginLeft": 40,
                    "autoMarginOffset": 20,
                    "mouseWheelZoomEnabled": true,
                    "dataDateFormat": "YYYY-MM-DD",
                    "valueAxes": [{
                        "id": "v1",
                        "axisAlpha": 0,
                        "position": "left",
                        "ignoreAxisWidth": true
                    }],
                    "balloon": {
                        "borderThickness": 1,
                        "shadowAlpha": 0
                    },
                    "graphs": [{
                        "id": "g1",
                        "balloon": {
                            "drop": true,
                            "adjustBorderColor": false,
                            "color": "#ffffff"
                        },
                        "bullet": "round",
                        "bulletBorderAlpha": 1,
                        "bulletColor": "#FFFFFF",
                        "bulletSize": 5,
                        "hideBulletsCount": 50,
                        "lineThickness": 2,
                        "title": "red line",
                        "useLineColorForBulletBorder": true,
                        "valueField": "value",
                        "balloonText": "<span style='font-size:18px;'>[[value]]</span>"
                    }],
                    "categoryField": "date",
                    "categoryAxis": {
                        "parseDates": true,
                        "dashLength": 1,
                        "minorGridEnabled": true
                    },
                    "export": {
                        "enabled": true
                    },
                    "titles": [{
                        "text": "주간 체중 변화"
                    }],
                    "dataProvider": [{
                        "date": "2012-07-27",
                        "value": 13
                        }, {
                        "date": "2012-07-28",
                        "value": 11
                        }, {
                        "date": "2012-07-29",
                        "value": 15
                        }, {
                        "date": "2012-07-30",
                        "value": 16
                        }, {
                        "date": "2012-07-31",
                        "value": 18
                        }, {
                        "date": "2012-08-01",
                        "value": 13
                        }, {
                        "date": "2012-08-02",
                        "value": 22
                        }, {
                        "date": "2012-08-03",
                        "value": 23
                        }, {
                        "date": "2012-08-04",
                        "value": 20
                        }, {
                        "date": "2012-08-05",
                        "value": 17
                        }, {
                        "date": "2012-08-06",
                        "value": 16
                        }, {
                        "date": "2012-08-07",
                        "value": 18
                        }]
                });

                weight_chart.addListener("rendered", zoomChart);

                zoomChart();

                function zoomChart() {
                    weight_chart.zoomToIndexes(weight_chart.dataProvider.length - 40, weight_chart.dataProvider.length - 1);
                }
            });

            $.each(info, function (key, item) {
                var objective_chart = AmCharts.makeChart("objective", {
                    "theme": "light",
                    "type": "gauge",
                    "axes": [{
                        "topTextFontSize": 20,
                        "topTextYOffset": 70,
                        "axisColor": "#31d6ea",
                        "axisThickness": 1,
                        "endValue": 100,
                        "gridInside": true,
                        "inside": true,
                        "radius": "50%",
                        "valueInterval": 10,
                        "tickColor": "#67b7dc",
                        "startAngle": -90,
                        "endAngle": 90,
                        "unit": "%",
                        "bandOutlineAlpha": 0,
                        "bands": [{
                            "color": "#0080ff",
                            "endValue": 100,
                            "innerRadius": "105%",
                            "radius": "170%",
                            "gradientRatio": [0.5, 0, -0.5],
                            "startValue": 0
                            }, {
                            "color": "#3cd3a3",
                            "endValue": 0,
                            "innerRadius": "105%",
                            "radius": "170%",
                            "gradientRatio": [0.5, 0, -0.5],
                            "startValue": 0
                        }]
                    }],
                    "arrows": [{
                        "alpha": 1,
                        "innerRadius": "35%",
                        "nailRadius": 0,
                        "radius": "170%"
                    }]
                });

            });
        });
    });
});

function updateLabels(event) {
    var labels = event.chart.chartDiv.getElementsByClassName("amcharts-axis-title");
    for (var i = 0; i < labels.length; i++) {
        var color = event.chart.dataProvider[i].color;
        if (color !== undefined) {
            labels[i].setAttribute("fill", color);
        }
    }
}



function makeWeekSelectOptions() {
    var year = $("#sh_year").val();
    var month = $("#sh_month").val();

    var today = new Date();

    var sdate = new Date(year, month - 1, 01);
    var lastDay = (new Date(sdate.getFullYear(), sdate.getMonth() + 1, 0)).getDate();
    var endDate = new Date(sdate.getFullYear(), sdate.getMonth(), lastDay);

    var week = sdate.getDay();
    sdate.setDate(sdate.getDate() - week);
    var edate = new Date(sdate.getFullYear(), sdate.getMonth(), sdate.getDate());
    var obj = document.getElementById("sh_week");
    obj.options.length = 0;
    var seled = "";
    while (endDate.getTime() >= edate.getTime()) {

        var sYear = sdate.getFullYear();
        var sMonth = (sdate.getMonth() + 1);
        var sDay = sdate.getDate();

        sMonth = (sMonth < 10) ? "0" + sMonth : sMonth;
        sDay = (sDay < 10) ? "0" + sDay : sDay;

        var stxt = sYear + "-" + sMonth + "-" + sDay;

        edate.setDate(sdate.getDate() + 6);

        var eYear = edate.getFullYear();
        var eMonth = (edate.getMonth() + 1);
        var eDay = edate.getDate();

        eMonth = (eMonth < 10) ? "0" + eMonth : eMonth;
        eDay = (eDay < 10) ? "0" + eDay : eDay;

        var etxt = eYear + "-" + eMonth + "-" + eDay;

        if (today.getTime() >= sdate.getTime() && today.getTime() <= edate.getTime()) {
            seled = stxt + "|" + etxt;
        }

        obj.options[obj.options.length] = new Option(stxt + "~" + etxt, stxt + "|" + etxt);

        sdate = new Date(edate.getFullYear(), edate.getMonth(), edate.getDate() + 1);
        edate = new Date(sdate.getFullYear(), sdate.getMonth(), sdate.getDate());
        start_date=sdate;
        end_date=edate;
    }

    if (seled) obj.value = seled;
   
    var select_year = document.getElementById('sh_year');
    console.log(select_year.options[select_year.selectedIndex].value);
    var select_month = document.getElementById('sh_month');
    console.log(select_month.options[select_month.selectedIndex].value);

}
