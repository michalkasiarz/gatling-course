var stats = {
    type: "GROUP",
name: "Global Information",
path: "",
pathFormatted: "group_missing-name-b06d1",
stats: {
    "name": "Global Information",
    "numberOfRequests": {
        "total": "300",
        "ok": "300",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "4",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "149",
        "ok": "149",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "9",
        "ok": "9",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "11",
        "ok": "11",
        "ko": "-"
    },
    "percentiles1": {
        "total": "7",
        "ok": "7",
        "ko": "-"
    },
    "percentiles2": {
        "total": "9",
        "ok": "9",
        "ko": "-"
    },
    "percentiles3": {
        "total": "19",
        "ok": "19",
        "ko": "-"
    },
    "percentiles4": {
        "total": "36",
        "ok": "36",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 300,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "15",
        "ok": "15",
        "ko": "-"
    }
},
contents: {
"req_get-all-video-g-6a25e": {
        type: "REQUEST",
        name: "Get all video games",
path: "Get all video games",
pathFormatted: "req_get-all-video-g-6a25e",
stats: {
    "name": "Get all video games",
    "numberOfRequests": {
        "total": "200",
        "ok": "200",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "4",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "149",
        "ok": "149",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "13",
        "ok": "13",
        "ko": "-"
    },
    "percentiles1": {
        "total": "7",
        "ok": "7",
        "ko": "-"
    },
    "percentiles2": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    },
    "percentiles3": {
        "total": "21",
        "ok": "21",
        "ko": "-"
    },
    "percentiles4": {
        "total": "39",
        "ok": "39",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 200,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "10",
        "ok": "10",
        "ko": "-"
    }
}
    },"req_get-specific-ga-03796": {
        type: "REQUEST",
        name: "Get specific game",
path: "Get specific game",
pathFormatted: "req_get-specific-ga-03796",
stats: {
    "name": "Get specific game",
    "numberOfRequests": {
        "total": "100",
        "ok": "100",
        "ko": "0"
    },
    "minResponseTime": {
        "total": "4",
        "ok": "4",
        "ko": "-"
    },
    "maxResponseTime": {
        "total": "27",
        "ok": "27",
        "ko": "-"
    },
    "meanResponseTime": {
        "total": "7",
        "ok": "7",
        "ko": "-"
    },
    "standardDeviation": {
        "total": "3",
        "ok": "3",
        "ko": "-"
    },
    "percentiles1": {
        "total": "6",
        "ok": "6",
        "ko": "-"
    },
    "percentiles2": {
        "total": "7",
        "ok": "7",
        "ko": "-"
    },
    "percentiles3": {
        "total": "12",
        "ok": "12",
        "ko": "-"
    },
    "percentiles4": {
        "total": "14",
        "ok": "14",
        "ko": "-"
    },
    "group1": {
    "name": "t < 800 ms",
    "count": 100,
    "percentage": 100
},
    "group2": {
    "name": "800 ms < t < 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group3": {
    "name": "t > 1200 ms",
    "count": 0,
    "percentage": 0
},
    "group4": {
    "name": "failed",
    "count": 0,
    "percentage": 0
},
    "meanNumberOfRequestsPerSecond": {
        "total": "5",
        "ok": "5",
        "ko": "-"
    }
}
    }
}

}

function fillStats(stat){
    $("#numberOfRequests").append(stat.numberOfRequests.total);
    $("#numberOfRequestsOK").append(stat.numberOfRequests.ok);
    $("#numberOfRequestsKO").append(stat.numberOfRequests.ko);

    $("#minResponseTime").append(stat.minResponseTime.total);
    $("#minResponseTimeOK").append(stat.minResponseTime.ok);
    $("#minResponseTimeKO").append(stat.minResponseTime.ko);

    $("#maxResponseTime").append(stat.maxResponseTime.total);
    $("#maxResponseTimeOK").append(stat.maxResponseTime.ok);
    $("#maxResponseTimeKO").append(stat.maxResponseTime.ko);

    $("#meanResponseTime").append(stat.meanResponseTime.total);
    $("#meanResponseTimeOK").append(stat.meanResponseTime.ok);
    $("#meanResponseTimeKO").append(stat.meanResponseTime.ko);

    $("#standardDeviation").append(stat.standardDeviation.total);
    $("#standardDeviationOK").append(stat.standardDeviation.ok);
    $("#standardDeviationKO").append(stat.standardDeviation.ko);

    $("#percentiles1").append(stat.percentiles1.total);
    $("#percentiles1OK").append(stat.percentiles1.ok);
    $("#percentiles1KO").append(stat.percentiles1.ko);

    $("#percentiles2").append(stat.percentiles2.total);
    $("#percentiles2OK").append(stat.percentiles2.ok);
    $("#percentiles2KO").append(stat.percentiles2.ko);

    $("#percentiles3").append(stat.percentiles3.total);
    $("#percentiles3OK").append(stat.percentiles3.ok);
    $("#percentiles3KO").append(stat.percentiles3.ko);

    $("#percentiles4").append(stat.percentiles4.total);
    $("#percentiles4OK").append(stat.percentiles4.ok);
    $("#percentiles4KO").append(stat.percentiles4.ko);

    $("#meanNumberOfRequestsPerSecond").append(stat.meanNumberOfRequestsPerSecond.total);
    $("#meanNumberOfRequestsPerSecondOK").append(stat.meanNumberOfRequestsPerSecond.ok);
    $("#meanNumberOfRequestsPerSecondKO").append(stat.meanNumberOfRequestsPerSecond.ko);
}
