<script setup>
import TheWelcome from '../components/TheWelcome.vue'
</script>

<template>
  <main>
    <div id="username-page" :class="defclass">
      <div class="username-page-container">
        <h1 class="title">Type your username to enter the Chatroom</h1>
        <form id="usernameForm" name="usernameForm" @submit.prevent="connect">
          <div class="form-group">
            <input type="text" id="name" v-model="username" placeholder="Username" autocomplete="off"
              class="form-control" />
          </div>
          <div class="form-group">
            <button type="submit" class="accent username-submit">Start Chatting</button>
          </div>
        </form>
      </div>
    </div>

    <div id="chat-page" :class="chatclass">
      <div class="chat-container">
        <div class="chat-header">
          <h2>Spring WebSocket Chat Demo - By Alibou</h2>
        </div>
        <div :class="connectingClass" v-if="!isError">
          Connecting...
        </div>
        <div :class="connectingClass" v-else="isError">
          Could not Connect To Websocket.
        </div>
        <ul id="messageArea">
          <li v-for="(row, index) in messageList" :key="index">
            <p>{{ row }}</p>
          </li>
        </ul>
        <form id="messageForm" name="messageForm" @submit.prevent="sendMessage">
          <div class="form-group">
            <div class="input-group clearfix">
              <input v-model="messageInput" type="text" id="message" placeholder="Type a message..." autocomplete="off"
                class="form-control" />
              <button type="submit" class="primary">Send</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </main>
</template>

<script>

export default {
  data() {
    return {
      stompClient: null,
      username: null,
      colors: [
        '#2196F3', '#32c787', '#00BCD4', '#ff5652',
        '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
      ],
      defclass: '',
      chatclass: 'hidden',
      connectingClass: '',
      isError: false,
      messageInput: '',
      messageList : [],


    }
  },

  methods: {
    connect() {
      this.defclass = "hidden";
      this.chatclass = '';

      // var socket = new SockJS('/ws');
      const socket = new SockJS('//localhost:8080/ws'); // Buraya backend'in adresini ve portunu girin
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect({}, this.onConnect, this.onError)
    },
    onConnect() {
      this.stompClient.subscribe('/topic/public', this.onMessageReceived);

      this.stompClient.send('/app/chat.addUser', {}, JSON.stringify({
        sender: this.username,
        type: 'JOIN'
      }));
      this.connectingClass = 'hidden';
    },
    onMessageReceived(payload) {
      var message = JSON.parse(payload.body);

      if (message.type === 'JOIN') {
        this.messageList.push((message.sender + ' joined.'));
      }else if (message.type === 'LEAVE') {
        this.messageList.push((message.sender + ' left.'));
      }else {
        
        this.messageList.push(message.content);
      }
    },
    onError() {
      this.isError = true;
    },
    sendMessage() {
      var messageContent = this.messageInput;

      if (messageContent && this.stompClient) {
        var chatMessage = {
          'sender': this.username,
          'content': messageContent,
          'type': 'CHAT',
        };
        this.stompClient.send(
          '/app/chat.sendMessage',
          {},
          JSON.stringify(chatMessage)
        );
        this.messageInput = '';
      }
    }

  }
}

</script>


<script setup>

</script>

<style></style>