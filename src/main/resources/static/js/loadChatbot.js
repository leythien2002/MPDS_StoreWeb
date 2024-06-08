// loadChatbot.js
document.addEventListener('DOMContentLoaded', function() {
    fetch('chatbot.html')
        .then(response => response.text())
        .then(data => {
            const chatboxContainer = document.createElement('div');
            chatboxContainer.innerHTML = data;
            document.body.appendChild(chatboxContainer);
        })
        .catch(error => console.error('Error loading chatbox:', error));
});
