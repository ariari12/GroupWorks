document.addEventListener('DOMContentLoaded', function() {
    loadChatList();
});

function loadChatList() {
    var chatList = [
        { id: 1, name: '영업 부서' },
        { id: 2, name: '인사 부서' },
        { id: 3, name: '기술 부서' }
    ];

    var chatListElement = document.getElementById('chatList');
    chatList.forEach(function(chat) {
        var listItem = document.createElement('li');
        listItem.classList.add('list-group-item');
        listItem.textContent = chat.name;
        listItem.setAttribute('data-chat-id', chat.id);
        listItem.addEventListener('click', function() {
            loadChatHistory(chat.id);
        });
        chatListElement.appendChild(listItem);
    });
}

function loadChatHistory(chatId) {
    var chatHistory = {
        1: [
            { type: 'incoming', text: '안녕하세요!', time: '오후 2:58' },
            { type: 'outgoing', text: '안녕하세요! 어떻게 도와드릴까요?', time: '오후 2:58' }
        ],
        2: [
            { type: 'incoming', text: '휴가 신청 관련 문의입니다.', time: '오후 2:58' },
            { type: 'outgoing', text: '네, 말씀하세요.', time: '오후 2:58' }
        ],
        3: [
            { type: 'incoming', text: '서버 점검 일정 문의드립니다.', time: '오후 2:58'},
            { type: 'outgoing', text: '내일 오전 10시에 예정되어 있습니다.', time: '오후 2:58'},
            { type: 'incoming', text: 'a', time: '오후 2:58' },
            { type: 'outgoing', text: 'ㅁ', time: '오후 2:58' },
            { type: 'outgoing', text: '0', time: '오후 2:58' }
        ]
    };

    var chatBox = document.getElementById('chatBox');
    chatBox.innerHTML = '';

    (chatHistory[chatId] || []).forEach(function(message) {
        var messageElement = document.createElement('div');
        messageElement.classList.add('message');

        if (message.type === 'incoming') {
            messageElement.innerHTML = `
                <div style="margin-bottom: 5px;">
                    <span>개발팀 이송 팀장</span>
                </div>
                <div class="incoming_msg">
                    <div class="received_msg">
                        <div class="received_withd_msg">
                            <p>${message.text}</p>
                            <span class="received_sent_time_date">${message.time}</span>
                        </div>
                    </div>
                </div>
            `;
        } else {
            messageElement.innerHTML = `
                <div class="outgoing_msg">
                    <div class="sent_msg">
                        <p>${message.text}</p>
                        <span class="sent_time_date">${message.time}</span>
                    </div>
                </div>
            `;
        }
        chatBox.appendChild(messageElement);
    });

    chatBox.scrollTop = chatBox.scrollHeight;
}

function sendMessage() {
    var messageInput = document.getElementById("messageInput");
    var messageText = messageInput.value;
    if (messageText.trim() === "") {
        return;
    }

    function escapeHtml(text) {
        var map = {
            '&': '&amp;',
            '<': '&lt;',
            '>': '&gt;',
            '"': '&quot;',
            "'": '&#039;'
        };
        return text.replace(/[&<>"']/g, function(m) { return map[m]; });
    }

    var chatBox = document.getElementById("chatBox");
    var outgoingMessage = document.createElement("div");
    outgoingMessage.classList.add("message");
    outgoingMessage.innerHTML = `
        <div class="outgoing_msg">
            <div class="sent_msg">
                <p>${(escapeHtml(messageText)).replace(/\n/g, '<br>')}</p>
                <span class="sent_time_date">${new Date().toLocaleTimeString(
                    'ko-KR', {
                        hour: 'numeric', minute: 'numeric', hour12: true 
                    })}</span>
            </div>
        </div>
    `;
    chatBox.appendChild(outgoingMessage);
    chatBox.scrollTop = chatBox.scrollHeight;
    messageInput.value = "";
}

function searchPeople() {
    var searchInput = document.getElementById('searchInput').value.toLowerCase();
    var searchResultsElement = document.getElementById('searchResults');
    searchResultsElement.innerHTML = '';

    var people = [
        { id: 1, name: '김철수' },
        { id: 2, name: '이영희' },
        { id: 3, name: '박영수' },
        { id: 4, name: '최민수' },
    ];

    var filteredPeople = people.filter(function(person) {
        return person.name.toLowerCase().includes(searchInput);
    });

    filteredPeople.forEach(function(person) {
        var listItem = document.createElement('li');
        listItem.classList.add('list-group-item');
        listItem.textContent = person.name;
        listItem.setAttribute('data-person-id', person.id);
        listItem.addEventListener('click', function() {
            selectPerson(person.id, person.name);
        });
        searchResultsElement.appendChild(listItem);
    });
}

function selectPerson(personId, personName) {
    var selectedPerson = { id: personId, name: personName };
    // 여기에서 선택된 사람을 관리합니다.
    // 예를 들어, 선택된 사람을 배열에 추가하거나 UI에 표시할 수 있습니다.
}

function createChatRoom() {
    $('#newChatModal').modal('hide');
    loadChatList();
    // 채팅방 만드는 로직 추가.
}

$(document).ready(function () {
    $('#messageInput').keypress(function(e){
        if (e.key === 'Enter' && e.shiftKey) {
            return true;
        } else if(e.keyCode === 13){
            sendMessage();
            return false;
        }
    });
});

