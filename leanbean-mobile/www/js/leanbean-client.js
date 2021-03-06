var leanbeanClient = {

    //_baseUrl: "http://10.0.2.2:8080/v1/",
    _baseUrl: "http://api.leanbean.wisobi.com/leanbean/v1/",

    postDevice: function(device, successCallback) {
        var url = this._baseUrl + "devices/";
        $.ajax({
                   headers: {
                       'Accept': 'application/json; charset=utf-8',
                       'Content-Type': 'application/json; charset=utf-8'
                   },
                   type: 'POST',
                   url: url,
                   datatype: 'json',
                   data: JSON.stringify(device),
                   success: function (deviceTO) {
                       console.log("Successfully added device.");
                       if (successCallback) successCallback(deviceTO);
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       alert('Oops! Something went wrong when connecting to the server.');
                   }
               })
    },

    putDevice: function(device, successCallback) {
        var url = this._baseUrl + "devices/" + device.id;
        $.ajax({
                   headers: {
                       'Accept': 'application/json; charset=utf-8',
                       'Content-Type': 'application/json; charset=utf-8'
                   },
                   type: 'PUT',
                   url: url,
                   datatype: 'json',
                   data: JSON.stringify(device),
                   success: function (deviceTO) {
                       console.log("Successfully updated device.");
                       if (successCallback) successCallback(deviceTO);
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       alert('Oops! Something went wrong when connecting to the server.');
                   }
               })
    },

    getMeeting: function (meetingId, successCallback, errorCallback) {
        if (meetingId == '' || meetingId == null) {
            return;
        }
        var url = this._baseUrl + "meetings/" + meetingId;
        $.ajax({
                   type: "GET",
                   url: url,
                   datatype: "json",
                   success: function (meetingTO) {
                       console.log("Successfully fetched meeting with id = " + meetingTO.id);
                       if (successCallback) successCallback(meetingTO);
                   },
                   error: function (jqXHR, ajaxOptions, thrownError) {
                       if (jqXHR.status == 404) {
                           alert('Oops! LeanBean could not find a meeting with id ' + meetingId + '.' +
                           '\nNote that meeting ids are case-sensitive.');
                       } else {
                           alert('Oops! Something went wrong when connecting to the server.');
                       }
                       if (errorCallback) errorCallback();
                   }
               })
    },

    getDeviceMeetings: function (deviceId, successCallback, errorCallback) {
        if (deviceId == '' || deviceId == null) {
            return;
        }
        var url = this._baseUrl + "devices/" + deviceId + "/meetings/";
        $.ajax({
            type: "GET",
            url: url,
            datatype: "json",
            success: function (meetingTOs) {
                console.log("Successfully fetched meetings for device.");
                if (successCallback) successCallback(meetingTOs);
            },
            error: function (jqXHR, ajaxOptions, thrownError) {
                if (jqXHR.status == 404) {
                    alert('Oops! LeanBean could not find meetings with activity from this device.');
                } else {
                    alert('Oops! Something went wrong when connecting to the server.');
                }
                if (errorCallback) errorCallback();
            }
        })
    },

    postMeeting: function (meeting, successCallback) {
        var url = this._baseUrl + "meetings/";
        $.ajax({
                   headers: {
                       'Accept': 'application/json; charset=utf-8',
                       'Content-Type': 'application/json; charset=utf-8'
                   },
                   type: 'POST',
                   url: url,
                   datatype: 'json',
                   data: JSON.stringify(meeting),
                   success: function (meetingTO) {
                       console.log("Successfully added meeting.");
                       if(successCallback) successCallback(meetingTO);
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       alert('Oops! Something went wrong when connecting to the server.');
                   }
               })
    },

    postTopic: function (topic) {
        var url = this._baseUrl + "topics/";
        $.ajax({
                   headers: {
                       'Accept': 'application/json; charset=utf-8',
                       'Content-Type': 'application/json; charset=utf-8'
                   },
                   type: 'POST',
                   url: url,
                   datatype: 'json',
                   data: JSON.stringify(topic),
                   success: function () {
                       console.log("Successfully added topic.");                   }
               })
    },

    putVote: function (vote) {
        var url = this._baseUrl + "meetings/" + vote.meetingId + "/devices/" + vote.deviceId + "/votes/";
        $.ajax({
                   headers: {
                       'Accept': 'application/json; charset=utf-8',
                       'Content-Type': 'application/json; charset=utf-8'
                   },
                   type: 'PUT',
                   url: url,
                   datatype: 'json',
                   data: JSON.stringify(vote),
                   success: function () {
                       console.log("Successfully added vote.")
                   }
               })
    },

    deleteTopic: function(topicId) {
        if (topicId == '' || topicId == null) {
            return;
        }
        var url = this._baseUrl + "topics/" + topicId;
        $.ajax({
                   type: "DELETE",
                   url: url,
                   datatype: "json",
                   success: function () {
                       console.log("Successfully deleted topic with id = " + topicId);
                   },
                   error: function (jqXHR, ajaxOptions, thrownError) {
                       alert('Oops! Something went wrong when connecting to the server.');
                   }
               })
    },

    _getDevice: function (deviceUuid, successCallback, errorCallback) {
        console.log("_getDevice: device.uuid = " + deviceUuid);
        var url = this._baseUrl + "device-uuid/" + deviceUuid;
        $.ajax({
                   type: "GET",
                   url: url,
                   datatype: "jsonp",
                   success: function (deviceTO) {
                       console.log("Successfully fetched device.");
                       if (successCallback) successCallback(deviceTO);
                   },
                   error: function (xhr, ajaxOptions, thrownError) {
                       if (xhr.status == 404) {
                           console.log('Error _getDevice: HTTP status ' + xhr.status);
                           if (errorCallback) errorCallback();
                       } else {
                           alert('Oops! Something went wrong when connecting to the server.');
                       }

                   }
               })
    },

    postEventJoinMeeting: function (event) {
        var url = this._baseUrl + "events/join-meeting/";
        $.ajax({
                   headers: {
                       'Accept': 'application/json; charset=utf-8',
                       'Content-Type': 'application/json; charset=utf-8'
                   },
                   type: 'POST',
                   url: url,
                   datatype: 'json',
                   data: JSON.stringify(event),
                   success: function () {
                       console.log("Successfully logged join-meeting event.");
                   },
                   error: function (xhr, ajaxOptions, thrownError) {
                       console.log('Error postEventJoinMeeting: HTTP status ' + xhr.status);
                   }
               })
    }

};