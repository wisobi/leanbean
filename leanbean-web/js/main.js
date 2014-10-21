function getUrlParameters() {
    var params = [], pair,
    // Retrieve the query string containing field value pairs
        pairs = window.location.search.substr(1).split('&');
    for (var i = 0; i < pairs.length; i++) {
        pair = pairs[i].split('=');
        if (pair.length != 2) {
            continue;
        }
        params.push(pair[0]);
        params[pair[0]] = decodeURIComponent(pair[1].replace(/\+/g, " "));
    }
    return params;
}

var leanbean = {

    _baseUrl: "http://localhost:8080/v1/meetings/",

    _elements: {
        meetingContainer: document.querySelector('#meeting_container'),
        meetingHeader: document.querySelector('#meeting_header'),
        topicsContainer: document.querySelector('#topics_container')
    },

    init: function (meetingId) {
        var url = this._baseUrl + meetingId;
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
        this._elements.meetingHeader.innerHTML = meeting.title;
        for (var i = 0; i < meeting.topics.length; i++) {
            this._generateTopicDiv(meeting.topics[i]);
        }
        this._updatePortlets();
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
        this._elements.topicsContainer.appendChild(portletDiv);
    },

    _numVotes: function (topic) {
        if (topic.votes.length == 1 && topic.votes[0].user.name == null) {
            return 0;
        } else {
            return topic.votes.length;
        }
    },

    _generateVotesDiv: function (votes, topicDiv) {
        var div = document.createElement('div');
        var votesStr = "";
        for (var i = 0; i < votes.length; i++) {
            if (votes[i].user.name != null) {
                votesStr += votes[i].user.name + ", ";
            }
        }
        div.innerHTML = votesStr.slice(0, -2);
        topicDiv.appendChild(div);
    },

    _updatePortlets: function () {
        $(".column").sortable({
                                  connectWith: ".column",
                                  handle: ".portlet-header",
                                  cancel: ".portlet-toggle",
                                  placeholder: "portlet-placeholder ui-corner-all"
                              });
        $(".portlet")
            .addClass("ui-widget ui-widget-content ui-helper-clearfix ui-corner-all")
            .find(".portlet-header")
            .addClass("ui-corner-all")
            .prepend("<span class='ui-icon ui-icon-triangle-1-s portlet-toggle'></span>");

        $(".portlet-toggle").click(function () {
            var icon = $(this);
            icon.toggleClass("ui-icon-triangle-1-n ui-icon-triangle-1-s");
            icon.closest(".portlet").find(".portlet-content").toggle();
        })
    }
};

$(document).on

$(document).ready(function () {
    var params = getUrlParameters();
    console.log(params['m_id']);
    leanbean.init(params['m_id']);
});

