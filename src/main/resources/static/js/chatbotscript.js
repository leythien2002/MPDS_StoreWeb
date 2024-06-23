const chatInput = document.querySelector(".chat-input textarea");
const sendChatBtn = document.querySelector(".chat-input span");
const chatbox = document.querySelector(".chatbox");
const chatbotToggle = document.querySelector(".chatbot-toggler");
const chatbotCloseBtn = document.querySelector(".close-btn");

let userMessage;

const API_KEY = "";
const ASSISTANT_ID = "asst_BODEyHM1GfUwLFT9UKsRvy9u";  // Replace with your actual assistant ID

const inputIniHeight = chatInput.scrollHeight;
const apiUrl = 'https://api.openai.com/v1/beta/threads';


async function createChatThread() {
  try {
    const response = await fetch("https://api.openai.com/v1/threads/runs", {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${API_KEY}`,
        "OpenAI-Beta": "assistants=v2"
      },
      body: JSON.stringify({
        "assistant_id": ASSISTANT_ID,
        "thread":{
          "messages": [
          {"role": "user", "content": userMessage}
        ]
        }
      })
    });

    if (!response.ok) {
      throw new Error('Failed to create chat thread');
    }
    const thread = await response.json();
    console.log(thread);

    const threadId = thread.thread_id;
    // const messages = thread.data.messages;

    console.log('Thread created with ID:', threadId);
    // console.log('messages: ', messages);

    return threadId

  } catch (error) {
    console.error('Error:', error);
    return null;
  }

}
async function continueConversation(id, incomingChatLi) {
  const messageElement = incomingChatLi.querySelector("p");

  try {
    const resolvedId = await id;
    await new Promise(resolve => setTimeout(resolve, 10000));
     const messageElement = incomingChatLi.querySelector("p");

    const response = await fetch(`https://api.openai.com/v1/threads/${resolvedId}/messages`, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${API_KEY}`,
        "OpenAI-Beta": "assistants=v2"
      }
      
    });
    const responseData = await response.json();
    const content = responseData.data[0].content[0].text.value
    console.log("aaaaaaaaaaa"+ content)

    messageElement.textContent = content;

  } catch (error) {
    console.error('Error fetching chat thread responses:', error);
    messageElement.classList.add("error");

    messageElement.textContent = "Something went wrong!";
    throw error; // Propagate the error for handling at higher level
  }finally{
    chatbox.scrollTo(0, chatbox.scrollHeight)
  }
  
}

 
const generateResponse = (incomingChatLi) => {
  const API_URL = "https://api.openai.com/v1/engines/gpt-3.5-turbo/completions";
  const messageElement = incomingChatLi.querySelector("p");

  const requestOptions = {
    method: "POST",
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${API_KEY}`,
    },
    body: JSON.stringify({
      messages: [
        {
          "role": "user",
          "content": userMessage,
        }
      ],
      assistant_id: ASSISTANT_ID,  // Include the assistant ID here
      max_tokens: 50,  // Adjust as needed
    }),
  }
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
    className === "outgoing" ? `<p></p>`: `<span class="material-symbols-outlined">smart_toy</span><p></p>`;
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

    // generateResponse(incomingChatLi);
    threadiddataosaomatrungdc = createChatThread()
    console.log(threadiddataosaomatrungdc,incomingChatLi)
    // temp(threadiddataosaomatrungdc, incomingChatLi)
    continueConversation(threadiddataosaomatrungdc, incomingChatLi)
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



