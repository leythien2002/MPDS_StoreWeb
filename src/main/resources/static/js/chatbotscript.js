const chatInput = document.querySelector(".chat-input textarea");
const sendChatBtn = document.querySelector(".chat-input span");
const chatbox = document.querySelector(".chatbox");
const chatbotToggle = document.querySelector(".chatbot-toggler");
const chatbotCloseBtn = document.querySelector(".close-btn");

let userMessage;

const API_KEY = "sk-proj-I5yCGrJwfxPnaNjLfX94T3BlbkFJnR0YC1Ag4zOU2nnRohUR";

const inputIniHeight = chatInput.scrollHeight;

const generateResponse = (incomingChatLi) => {
  const API_URL = "https://api.openai.com/v1/chat/completions";
  const messageElement = incomingChatLi.querySelector("p");

  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${API_KEY}`,
    },
    body: JSON.stringify({
      model: "gpt-3.5-turbo",
      messages: [
        {
          role: "user",
          content: userMessage,
        }
      ],
    }),
  };
  fetch(API_URL, requestOptions).then(res =>res.json()).then(data => {
    console.log(data);
    messageElement.textContent = data.choices[0].message.content;
  }).catch((error)=>{
    console.log(error)
        messageElement.classList.add("error");

    messageElement.textContent = "Something went wrong!";

  }).finally(()=>   chatbox.scrollTo(0, chatbox.scrollHeight));

};

const createChatLi = (message, className) => {
  const chatLi = document.createElement("li");
  chatLi.classList.add("chat", className);
  let chatContent =
    className === "outgoing"
      ? `<p></p>`
      : `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
  chatLi.innerHTML = chatContent;
  chatLi.querySelector("p").textContent = message;
  return chatLi;
};

const handleChat = () => {
  userMessage = chatInput.value.trim();
  if (!userMessage) return;
  chatInput.value = "";
  chatInput.style.height=`${inputIniHeight}px`

  chatbox.appendChild(createChatLi(userMessage, "outgoing"));
  chatbox.scrollTo(0, chatbox.scrollHeight);

  setTimeout(() => {
    const incomingChatLi = createChatLi("Thinking...", "incoming");
    chatbox.appendChild(incomingChatLi);
    chatbox.scrollTo(0, chatbox.scrollHeight);

    generateResponse(incomingChatLi);
  }, 600);
};

chatInput.addEventListener("input", ()=>{
  chatInput.style.height=`${inputIniHeight}px`
  chatInput.style.height=`${chatInput.scrollHeight}px`

})
chatInput.addEventListener("keydown", (e)=>{
  if(e.key ==="Enter" && !e.shiftKey && window.innerWidth >800){
    e.preventDefault();
    handleChat();
  }

})
sendChatBtn.addEventListener("click", handleChat);
chatbotCloseBtn.addEventListener("click", ()=> document.body.classList.remove("show-chatbot"));
chatbotToggle.addEventListener("click", ()=> document.body.classList.toggle("show-chatbot"));



