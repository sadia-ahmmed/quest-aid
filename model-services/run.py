from models.llm_dir import google_palm


prompt = """
I have a table called survey_responses. Its schema is (response_id,survey_id,surveyor_id,response_data,submitted_at).

The survey questions are:
What medicine do you take the most? of type String and has an id of 10
do you take more than 3 pills per day? of type boolean and has an id of 11

The survey id is 7

Randomly generate 10 answers for these questions, and make them into INSERT SQL queries for the survey_responses table.
"""


res_str = google_palm.predict(prompt)
print(res_str)
