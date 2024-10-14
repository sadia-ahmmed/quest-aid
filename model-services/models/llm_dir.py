from langchain_community.llms.google_palm import GooglePalm
from langchain_community.chat_models import ChatGooglePalm
from configs import GOOGLE_API_KEY
from langchain_community.cache import InMemoryCache

llm_cache = InMemoryCache()
# openai_gpt = OpenAI(openai_api_key=OPENAI_API_KEY)
# chat_gpt_openai = ChatOpenAI(openai_api_key=OPENAI_API_KEY)
google_palm = GooglePalm(google_api_key=GOOGLE_API_KEY)
google_palm_chat = ChatGooglePalm(google_api_key=GOOGLE_API_KEY)
