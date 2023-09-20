from langchain.llms import OpenAI, GooglePalm
from langchain.chat_models import ChatOpenAI, ChatGooglePalm
from configs import OPENAI_API_KEY, GOOGLE_API_KEY
from models.customllm import CustomLLM
from langchain.cache import InMemoryCache

llm_cache = InMemoryCache()
openai_gpt = OpenAI(openai_api_key=OPENAI_API_KEY) # 
chat_gpt_openai = ChatOpenAI(openai_api_key=OPENAI_API_KEY)
custom_llm = CustomLLM(n=100)
google_palm = GooglePalm(google_api_key=GOOGLE_API_KEY)
google_palm_chat = ChatGooglePalm(google_api_key=GOOGLE_API_KEY)