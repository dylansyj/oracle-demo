import requests
import random
import time
import argparse
import uuid
from datetime import datetime

ENDPOINT_URL = "http://localhost:8080/demo/survey"
DELAY_SECONDS = 1

GENDERS = ["male", "female", "other"]
SCORE_MIN = 1
SCORE_MAX = 5
AGE_MIN = 15
AGE_MAX = 80
REGIONS = ["SG", "US", "EU", "CN"]

def generate_random_survey():
    return {
        "score": random.randint(SCORE_MIN, SCORE_MAX),
        "gender": random.choice(GENDERS),
        "age": random.randint(AGE_MIN, AGE_MAX),
        "surveyID": str(uuid.uuid4()), 
        "region": random.choice(REGIONS)
    }

def send_survey(survey):
    headers = {"Content-Type": "application/json"}
    try:
        response = requests.post(ENDPOINT_URL, json=survey, headers=headers)
        print(f"[{datetime.now()}] Sent: {survey} | Status: {response.status_code}")
        if response.status_code != 200:
            print(f"Error: {response.text}")
    except requests.RequestException as e:
        print(f"Request failed: {e}")

def main():
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description="Generate random survey samples for testing.")
    parser.add_argument("--count", type=int, default=5, help="Number of survey samples to generate (default: 5)")
    args = parser.parse_args()

    num_requests = args.count
    if num_requests <= 0:
        print("Error: --count must be a positive integer.")
        return

    print(f"Starting sample generator. Sending {num_requests} requests to {ENDPOINT_URL}...")
    for i in range(num_requests):
        survey = generate_random_survey()
        send_survey(survey)
        time.sleep(DELAY_SECONDS)
    print("Finished sending samples.")

if __name__ == "__main__":
    main()