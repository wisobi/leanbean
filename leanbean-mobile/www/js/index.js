var _ws;

var leanbeanClient = {

    //_baseUrl: "http://10.0.2.2:8080/v1/",
    _baseUrl: "http://api.leanbean.wisobi.com/leanbean/v1/",

    postDevice: function(device, successCallback) {
        var url = this._baseUrl + "device/";
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
                       if (jqXHR.status == 400) {
                           alert('Error postDevice: HTTP status ' + jqXHR.status);
                       } else {
                           alert('Error postDevice: HTTP status ' + jqXHR.status);
                       }
                   }
               })
    },

    putDevice: function(device, successCallback) {
        var url = this._baseUrl + "device/" + device.id;
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
                       //leanbeanClient.login(deviceTO);
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                       if (jqXHR.status == 400) {
                           alert('Error putDevice: HTTP status ' + jqXHR.status);
                       } else {
                           alert('Error putDevice: HTTP status ' + jqXHR.status);
                       }
                   }
               })
    },

    getMeeting: function (meetingId, successCallback, errorCallback) {
        if (meetingId == '' || meetingId == null) {
            return;
        }
        var url = this._baseUrl + "meeting/" + meetingId;
        $.ajax({
                   type: "GET",
                   url: url,
                   datatype: "json",
                   success: function (meetingTO) {
                       console.log("Successfully fetched meeting with id = " + meetingTO.id);
                       if (successCallback) successCallback(meetingTO);
                       //leanbean._loadMeeting(meetingTO);
                   },
                   error: function (jqXHR, ajaxOptions, thrownError) {
                       if (jqXHR.status == 404) {
                           alert('404: Could not find a Lean Coffee with ID ' + meetingId);
                       } else {
                           alert('Error _getMeeting: HTTP status ' + jqXHR.status);
                       }
                       if (errorCallback) errorCallback();
                   }
               })
    },

    postMeeting: function (meeting, successCallback) {
        var url = this._baseUrl + "meeting/";
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
                       if (jqXHR.status == 400) {
                           alert('Error _postMeeting: HTTP status ' + jqXHR.status);
                       } else {
                           alert('Error _postMeeting: HTTP status ' + jqXHR.status);
                       }
                   }
               })
    },

    postTopic: function (topic) {
        var url = this._baseUrl + "topic/";
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
                       console.log("Successfully added topic.");
                       // leanbean._getAndLoadMeeting();
                   }
               })
    },

    putVote: function (vote) {
        var url = this._baseUrl + "vote/" + vote.meetingId + "/" + vote.deviceId;
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
        var url = this._baseUrl + "topic/" + topicId;
        $.ajax({
                   type: "DELETE",
                   url: url,
                   datatype: "json",
                   success: function () {
                       console.log("Successfully deleted topic with id = " + topicId);
                   },
                   error: function (jqXHR, ajaxOptions, thrownError) {
                       if (jqXHR.status == 404) {
                           alert('Error deleteTopic: HTTP status ' + jqXHR.status);
                       } else {
                           alert('Error deleteTopic: HTTP status ' + jqXHR.status);
                       }
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
                       //leanbean._handleLoginExistingDevice(deviceTO);
                   },
                   error: function (xhr, ajaxOptions, thrownError) {
                       if (xhr.status == 404) {
                           console.log('Error _getDevice: HTTP status ' + xhr.status);
                           if (errorCallback) errorCallback();
                           //leanbean._handleLoginNewDevice(deviceTO);
                       } else {
                           alert('Error _getDevice: HTTP status ' + xhr.status);
                       }

                   }
               })
    }

};

var leanbean = {

    _elements: {
        joinMeetingId: document.querySelector('#join-meeting-id'),
        meetingHeader: document.querySelector('#meeting-header'),
        topicsContainer: document.querySelector('#topics-container')
    },

    // LeanBean Constructor
    init: function () {
        this._bindEvents();
    },

    // Bind event listeners
    _bindEvents: function () {
        $(document).on('click', '#home-meeting-join-button', this._homeMeetingJoinButtonClick.bind(this));
        $(document).on('click', '#home-meeting-add-button', this._homeMeetingAddButtonClick.bind(this));
        $(document).on('click', '#home-settings-alias-button', this._homeSettingsAliasButtonClick.bind(this));
        $(document).on('click', '#home-device-add-button', this._homeDeviceAddButtonClick.bind(this));
        $(document).on('click', '#meeting-topic-add-button', this._meetingTopicAddButtonClick.bind(this));
        $(document).on('click', '#meeting-topic-delete-button', this._meetingTopicDeleteConfirm.bind(this));
        $(document).on('change', '#meeting-facilitate-checkbox', this._meetingFacilitateCheckboxChange.bind(this));

        $(document).on("pagecontainerbeforeshow",
                       this._pageContainerBeforeShow.bind(this));
    },

    // Static Event Handlers

    _pageContainerBeforeShow: function (event, ui) {
        var pageId = ui.toPage[0].id;
        if (pageId == 'meeting') {
            var meetingId = this._getState().meetingId;
            _ws = new WebSocket("ws://api.leanbean.wisobi.com/leanbean/ws/meeting/" + meetingId);

            _ws.onerror = function(event) {

            };

            _ws.onclose = function(event) {

            };

            _ws.onmessage = function(event) {
                var message = JSON.parse(event.data);
                console.log("_ws.onmessage()");
                console.log(message);
                if(message.type == 'meetingview') {
                    leanbean._loadMeeting(message);
                }

            };

            _ws.onopen = function(event) {

            };
        } else if (pageId == 'home') {
            if(_ws) {
                _ws.close();
            }
            // Home Page
            var recentMeetings = this._getRecentMeetings;
            for(var i = 0; i < recentMeetings.length; i++) {
                console.log("Recent meeting " + i + " has meetingId " + recentMeetings[i]);
            }
        }
    },

    _homeDeviceAddButtonClick: function(event) {
        var deviceForm = this._formDataToJSON('#home-device-add-form');
        console.log(deviceForm);
        console.log('_homeDeviceAddButtonClick: deviceForm.alias = ' + deviceForm.alias);

        var deviceTO = {};
        deviceTO.alias = deviceForm.alias;
        deviceTO.uuid = device.uuid;

        this._setSetting("device", deviceTO);
        leanbeanClient.postDevice(deviceTO, leanbean.login.bind(this));
    },

    _homeMeetingJoinButtonClick: function (event) {
        var meetingId = document.querySelector('#home-meeting-join-id').value;
        this._setState("meetingId", meetingId);
        this._getAndLoadMeeting();
    },

    _homeMeetingAddButtonClick: function (event) {
        var meetingForm = this._formDataToJSON('#home-meeting-add-form');
        meetingForm.deviceId = this._getSetting("device").id;
        leanbeanClient.postMeeting(meetingForm, leanbean._loadMeeting.bind(this));
        // Change page to #meeting
        //$(":mobile-pagecontainer").pagecontainer("change", "#meeting");
    },

    _meetingTopicAddButtonClick: function (event) {
        var topicForm = this._formDataToJSON('#meeting-topic-add-form');
        if (topicForm.title == '' || topicForm.title == null) {
            // Do something
        }
        topicForm.deviceId = this._getSetting("device").id;
        topicForm.meetingId = this._getState().meetingId;
        leanbeanClient.postTopic(topicForm)

        $('#meeting-topic-add-title').val("");
        $('#meeting-topic-add-pitch').val("");

        $('#meeting-topic-add').panel('close');
    },

    _postTopicSuccessCallback: function() {
        leanbeanClient.login(device.uuid, leanbean._handleLoginExistingDevice, leanbean._handleLoginNewDevice);
    },

    _meetingTopicDeleteConfirm: function(event) {
        var topicId = this._getState().topicId;
        leanbeanClient.deleteTopic(topicId)
    },

    _meetingFacilitateCheckboxChange: function(event) {
        var checkbox = $('#meeting-facilitate-checkbox').val();
        this._setState("isFacilitator", checkbox == 'on' ? true : false);
        leanbeanClient.getMeeting(this._getState().meetingId, leanbean._loadMeeting.bind(this));
        $('#meeting-topic-add').panel('close');
    },

    // Dynamic Event Handlers

    _meetingTopicToggleButtonClick: function (event) {
        var toggleBtndiv = $('#' + event.target.id);
        var titleDiv = $(event.data.headingDiv);
        var topicDiv = $(event.data.topicDiv);
        var pitchDiv = $(event.data.pitchDiv);
        toggleBtndiv.toggleClass("ui-icon-plus");
        toggleBtndiv.toggleClass("ui-icon-minus");
        topicDiv.toggleClass("w-collapsible-collapsed");
        titleDiv.toggleClass("w-collapsible-heading-collapsed");
        pitchDiv.toggleClass("w-collapsible-content-collapsed");
    },

    _meetingTopicVoteButtonClick: function (event) {
        var divId = '#' + event.target.id;
        this._meetingTopicVoteButtonCheck(divId);

        var votedTopicDivs = $('div.w-collapsible-heading div.ui-icon-user[checked="checked"]');
        if (votedTopicDivs.length == 2) {
            // Generate
            var votedTopicIds = [];
            for (var i = 0; i < votedTopicDivs.length; i++) {
                // Example: id="meeting-topic-vote-button-5"
                var topicId = $(votedTopicDivs[i]).attr('id').substr(26);
                votedTopicIds.push(topicId);
            }
            var voteTO = {
                deviceId: this._getSetting('device').id,
                meetingId: this._getState().meetingId,
                topicIds: votedTopicIds
            }
            leanbeanClient.putVote(voteTO);
        }

        this._meetingTopicVoteButtonToggle();
    },

    _meetingTopicDeleteButtonClick: function(event) {
        // Example: id="meeting-topic-delete-button-5"
        var topicId = event.target.id.substr(28);
        this._setState("topicId", topicId);
        $("#meeting-topic-delete").popup("open");
    },

    _homeSettingsAliasButtonClick: function (event) {
        var alias = $('#home-settings-alias').val();
        var id = this._getSetting("device").id;
        var deviceTO = {};
        deviceTO.id = id;
        deviceTO.alias = alias;
        deviceTO.uuid = device.uuid;
        this._setSetting("device", deviceTO);
        leanbeanClient.putDevice(deviceTO, leanbean.login);
    },

    // Layout functions

    _getAndLoadMeeting: function() {
        var meetingId = this._getState().meetingId;
        this._setState("isFacilitator", false);
        this._addRecentMeetings(meetingId);
        console.log("meetingId = " + meetingId);
        leanbeanClient.getMeeting(meetingId, leanbean._loadMeeting.bind(this));
    },

    // Used when LeanBean is triggered via Custom URL Scheme
    getAndLoadMeeting: function(meetingId) {
        this._setState("meetingId", meetingId);
        this._getAndLoadMeeting();
    },

    _loadMeeting: function (meeting) {
        console.log("_loadMeeting(): loading meeting with id " + meeting.id);
        document.querySelector('#meeting-header-text').innerHTML = meeting.title;
        document.querySelector('#meeting-topic-set').innerHTML = '';
        for (var i = 0; i < meeting.topics.length; i++) {
            var topic = meeting.topics[i];
            this._generateTopicDiv(topic);
        }

        // Trigger votes on topics that device has voted on
        for (var i = 0; i < meeting.topics.length; i++) {
            var topic = meeting.topics[i];

            // Mark votes as checked on topics that device has voted on
            var deviceId = this._getSetting("device").id;
            $(topic.votes).each(function (key, value) {
                if (value.device.id == deviceId) {
                    var divId = '#meeting-topic-vote-button-' + topic.id;
                    leanbean._meetingTopicVoteButtonCheck(divId);
                    //$('#meeting-topic-vote-button-' + topic.id).trigger('click');
                }
            });
        }
        // Disable/Enable depending on number of votes
        this._meetingTopicVoteButtonToggle();

        $('#meeting-topic-set').find('div[data-role=controlgroup]').controlgroup();
        $('#meeting-footer-text').html("Lean Coffee ID: " + meeting.id);
        // Meeting page is loaded and ready to be viewed
        this._setState("meetingId", meeting.id);
        $(":mobile-pagecontainer").pagecontainer("change", "#meeting");
    },

    _generateTopicDiv: function (topic) {

        var state = this._getState();

        // Button divs
        var delBtnDiv = document.createElement('div');
        $(delBtnDiv).addClass('ui-btn ui-corner-all ui-icon-delete ui-btn-icon-notext')
            .attr('id', 'meeting-topic-delete-button-' + topic.id)
            .on('click', this._meetingTopicDeleteButtonClick.bind(this));

        var voteBtnDiv = document.createElement('div');
        $(voteBtnDiv).addClass('ui-btn ui-corner-all ui-icon-user ui-btn-icon-notext')
            .attr('id', 'meeting-topic-vote-button-' + topic.id)
            .on('click', this._meetingTopicVoteButtonClick.bind(this));

        var toggleBtnDiv = document.createElement('div');
        $(toggleBtnDiv).addClass('ui-btn ui-corner-all ui-icon-plus ui-btn-icon-notext')
            .attr('id', 'meeting-topic-toggle-button-' + topic.id)
            .on('click',
                {
                    topicDiv: '#meeting-topic-' + topic.id,
                    pitchDiv: '#meeting-topic-pitch-' + topic.id,
                    headingDiv: '#meeting-topic-title-' + topic.id
                },
                this._meetingTopicToggleButtonClick.bind(this));


        // Control group div
        var controlGroupDiv = document.createElement('div');
        $(controlGroupDiv).attr('data-role', 'controlgroup')
            .attr('data-type', 'horizontal')
            .attr('data-mini', 'true');
        if(state != null && state.isFacilitator != null && state.isFacilitator == true) {
            $(controlGroupDiv).append(delBtnDiv);
        }
        $(controlGroupDiv).append(voteBtnDiv)
            .append(toggleBtnDiv)
            .trigger('create');

        // Title div
        var titleDiv = document.createElement('div');
        var title = topic.title + ' (' + this._numVotes(topic) + ')';
        $(titleDiv).addClass('ui-btn ui-corner-all')
            .html(title);

        // Heading div
        var headingDiv = document.createElement('div');
        $(headingDiv).addClass('w-collapsible-heading w-collapsible-heading-collapsed')
            .attr('id', 'meeting-topic-title-' + topic.id)
            .append(controlGroupDiv)
            .append(titleDiv);

        // Pitch div
        var pitchDiv = document.createElement('div');
        $(pitchDiv).addClass('w-collapsible-content w-collapsible-content-collapsed ui-corner-all ui-body-inherit')
            .attr('id', 'meeting-topic-pitch-' + topic.id)
            .html('<p>' + topic.pitch + '</p>' + topic.device.alias);

        // Topic div
        var topicDiv = document.createElement('div');
        $(topicDiv).addClass('w-collapsible w-collapsible-collapsed')
            .attr("id", "meeting-topic-" + topic.id)
            .append(headingDiv)
            .append(pitchDiv);

        document.querySelector('#meeting-topic-set').appendChild(topicDiv);
    },

    _meetingTopicVoteButtonCheck: function(divId) {
        var div = $(divId);
        div.toggleClass("ui-btn-b");
        div.attr('checked', !div.attr('checked'));
    },

    _meetingTopicVoteButtonToggle: function() {
        var votedTopicDivs = $('div.w-collapsible-heading div.ui-icon-user[checked="checked"]');
        var notVotedTopicDivs = $('div.w-collapsible-heading div.ui-icon-user:not([checked="checked"])');

        if (votedTopicDivs.length == 2) {
            $(notVotedTopicDivs).each(function (index) {
                $(this).addClass("ui-state-disabled");
            });
        } else {
            $(notVotedTopicDivs).each(function (index) {
                $(this).removeClass("ui-state-disabled");
            });
        }
    },

    // Utility functions

    login: function (device) {
        leanbeanClient._getDevice(device.uuid, leanbean._handleLoginExistingDevice, leanbean._handleLoginNewDevice);
    },

    _handleLoginExistingDevice: function(device) {
        console.log("_handleLoginExistingDevice: device.id = " + device.id);
        $('#home-settings-alias').val(device.alias);
        leanbean._setSetting("device", device);
    },

    _handleLoginNewDevice: function() {
        console.log("_handleLoginNewDevice: device.uuid = " + device.uuid);
        $("#home-device-add").popup("open");
    },

    _formDataToJSON: function (formId) {
        var jsonObject = {};
        var formData = $(formId).serializeArray();
        for (i = 0; i < formData.length; i++) {
            var name = formData[i]["name"];
            var value = formData[i]["value"];
            jsonObject[name] = value;
        }
        return jsonObject;
    },

    _getSettings: function() {
        var settings = JSON.parse(localStorage.getItem("settings"));
        settings = settings == null ? {} : settings;
        return settings;
    },

    _getSetting: function(key) {
        var settings = this._getSettings();
        return settings[key];
    },

    _setSetting: function(key, value) {
        var settings = this._getSettings();
        settings[key] = value;
        localStorage.setItem("settings", JSON.stringify(settings));
    },

    _getState: function() {
        var state = JSON.parse(localStorage.getItem("state"));
        state = state == null ? {} : state;
        return state;
    },

    _setState: function(key, value) {
        var state = this._getState();
        state[key] = value;
        localStorage.setItem("state", JSON.stringify(state));
    },

    _numVotes: function (topic) {
        if (topic.votes.length == 1 && topic.votes[0].device.id == null) {
            return 0;
        } else {
            return topic.votes.length;
        }
    },

    _getRecentMeetings: function() {
        var recentMeetings = this._getState().recentMeetings;
        return recentMeeting == null ? [] : recentMeetings;
    },

    _addRecentMeetings: function(meetingId) {
        var recentMeetings = this._getState().recentMeetings;
        recentMeetings = recentMeetings == null ? [] : recentMeetings;

        // Check if meeting already is in stack
        var index;
        for (var i = 0; i < recentMeetings.length; i++) {
            if(recentMeetings[i] == meetingId) {
                index = i;
                break;
            }
        }

        if(index != null) {
            // Meeting is in stack, move it the top
            var oldTop = recentMeetings[0];
            recentMeetings.splice(index, 1);
            recentMeetings.push(meetingId);

        } else {
            // Meeting not in stack, add it to top and crop stack
            recentMeetings.push(meetingId);
            recentMeetings.splice(5);
        }
        this._setState("recentMeetings", recentMeetings);
    }
};

leanbean.init();