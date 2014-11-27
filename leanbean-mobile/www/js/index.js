/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        var parentElement = document.getElementById(id);
        var listeningElement = parentElement.querySelector('.listening');
        var receivedElement = parentElement.querySelector('.received');

        listeningElement.setAttribute('style', 'display:none;');
        receivedElement.setAttribute('style', 'display:block;');

        console.log('Received Event: ' + id);
    }
};

app.initialize();

var leanbean = {

    _baseUrl: "http://localhost:8080/v1/",

    _elements: {
        joinMeetingId: document.querySelector('#join-meeting-id'),
        meetingHeader: document.querySelector('#meeting-header'),
        topicsContainer: document.querySelector('#topics-container')
    },

    // LeanBean Constructor
    init: function() {
        this._bindEvents();
    },

    // Bind event listeners
    _bindEvents: function() {
        $(document).on('click', '#join-meeting-button', this._joinMeetingClick.bind(this));
        $(document).on("pagecontainerbeforetransition", this._pageContainerBeforeTransition.bind(this));
    },

    // Event handlers
    _joinMeetingClick: function(event) {
        // Create JSON object to hold data for page #join-meeting
        var meetingId = document.querySelector('#join-meeting-id').value; //this._elements.joinMeetingId.value;
        var joinMeetingData = {"meetingId": meetingId};
        localStorage.setItem("join-meeting", JSON.stringify(joinMeetingData));
        // Change page to #meeting
        $(":mobile-pagecontainer").pagecontainer("change", "#meeting");
    },

    // Load data from REST API or Localstorage before transition
    _pageContainerBeforeTransition: function (event, ui) {
        var pageId = ui.toPage[0].id;
        console.log("pageId " + pageId);
        if(pageId == 'meeting') {
            var joinMeetingData = JSON.parse(localStorage.getItem("join-meeting"));
            var meetingId = joinMeetingData.meetingId;
            console.log("meetingId = " + meetingId);
            $("#meeting_id").html(meetingId);
            this._getMeeting(meetingId);
        }
    },

    // REST
    _getMeeting: function (meetingId) {
        var url = this._baseUrl + "meeting/" + meetingId;
        $.ajax({
            type: "GET",
            url: url,
            datatype: "jsonp",
            success: function (meeting) {
                console.log("Successfully fetched meeting with id = " + meeting.id);
                console.dir(meeting);
                leanbean._updateMeeting(meeting);
            }
        })
    },

    _updateMeeting: function (meeting) {
        document.querySelector('#meeting-header').innerHTML = meeting.title; //this._elements.meetingHeader.innerHTML = meeting.title;
        for (var i = 0; i < meeting.topics.length; i++) {
            this._generateTopicDiv(meeting.topics[i]);
        }   
    },

    _generateTopicDiv: function (topic) {
        var portletDiv = document.createElement('div');
        portletDiv.className = "portlet";
        var portletHeaderDiv = document.createElement('div');
        portletHeaderDiv.className = "portlet-header";
        var portletContentDiv = document.createElement('div');
        portletContentDiv.className = "portlet-content";
        portletDiv.appendChild(portletHeaderDiv);
        portletDiv.appendChild(portletContentDiv);

        portletHeaderDiv.innerHTML = topic.title + " (" + this._numVotes(topic) + ")";
        portletContentDiv.innerHTML = topic.user.name;
        //this._generateVotesDiv(topic.votes, portletDiv);
        document.querySelector('#topics-container').appendChild(portletDiv); //this._elements.topicsContainer.appendChild(portletDiv);
    },

    _numVotes: function (topic) {
        if (topic.votes.length == 1 && topic.votes[0].user.name == null) {
            return 0;
        } else {
            return topic.votes.length;
        }
    }
}

leanbean.init();

function postMeeting() {
    // var url = leanbean._baseUrl + "meetings/";
    var url = "http://localhost:8080/v1/meetings/";
    var meeting = {
        "title": "New Posted Meeting",
        "user": {
            "id": 1
        },
        "duration": 0,
        "startDateTime": null
    };
    $.ajax({
               headers: {
                   'Accept': 'application/json',
                   'Content-Type': 'application/json'
               },
               type: "POST",
               url: url,
               datatype: "jsonp",
               data: {action : 'add-meeting', formData : $('#add-meeting-form').serialize()},
               success: function () {
                   console.log("Successfully posted meeting.");
               }
           })
}

/*
$("#join-meeting-button").click(function(){
    meeting_id = $("#join-meeting-id").val();
    settingsObject = { "meeting_id" : meeting_id } //put settings in an object
    localStorage.setItem('settings', JSON.stringify(settingsObject)); //store string in localSettings}
);

function storeMeetingId() {
  // store some data
  if(typeof(Storage)!=="undefined") {
    localStorage.setItem("meeting_id", 1);         
  }
  $(":mobile-pagecontainer").pagecontainer("change", "#meeting", { 
    transition: 'slide'
  });    
}

function getMeetingId() {
    var meeting_id = localStorage.getItem("meeting_id");
    $('#meeting_id').html('Meeting ' + meeting_id);
}

$(document).on("pagecreate", "#join-meeting", function() {
    $(":mobile-pagecontainer").on('pagecontainerbeforechange', function (event, ui) {

        alert("Test 1");
    });
});
/*
$(document).on("pagecontainerbeforechange", function(e, data) {
      $(document).on('click', '#join-meeting-button', function() {     
        // store some data
        if(typeof(Storage)!=="undefined") {
              localStorage.setItem("firstname="Dragan";
              localStorage.lastname="Gaic";            
        }
        // Change page
        if(typeof data.toPage == "object" && typeof data.absUrl == "undefined") {
            data.toPage = $("#meeting");
        }
    });  


});
*/