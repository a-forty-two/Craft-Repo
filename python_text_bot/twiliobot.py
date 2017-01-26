import os
from flask import Flask, request, Response
from slackclient import SlackClient
from twilio import twiml
from twilio.rest import TwilioRestClient
import pandas as pd


SLACK_TOKEN = 'xoxp-56410480688-56414984853-56406526998-0b7aa53128'
TWILIO_ACCOUNT_SID = 'AC87d216fef3c6fd4430c5959bcfda41a9'
TWILIO_AUTH_TOKEN = 'b70c6cc17d412a64f4f5eefc579ee928'
TWILIO_NUMBER = '+18472326419'
USER_NUMBER = '+18473736628'
SLACK_WEBHOOK_SECRET = '6NweVeVKBxUWThLIAj2fv9oM'


app = Flask(__name__)
slack_client = SlackClient(SLACK_TOKEN)
twilio_client = TwilioRestClient(TWILIO_ACCOUNT_SID, TWILIO_AUTH_TOKEN)


@app.route('/twilio', methods=['POST'])

def twilio_post():

    response = twiml.Response()
    global message

    if request.form['From'] == USER_NUMBER:


		if request.form['Body'] == 'Load':
			
			global data
			data = pd.read_csv('test.txt')
			message = 'Mobi has just loaded the data in memory for you.'


		if request.form['Body'] == 'Columns':
			columns = list(data.columns)
			message = columns


		if request.form['Body'] == 'Commands':
			message = 'Commands | Load | Columns'

		slack_client.api_call("chat.postMessage", channel="#general", text=message, username='twiliobot', icon_emoji=':robot_face:')


    return Response(response.toxml(), mimetype="text/xml"), 200



@app.route('/slack', methods=['POST'])

def slack_post():

	if request.form['token'] == SLACK_WEBHOOK_SECRET:


		channel = request.form['channel_name']
		username = request.form['user_name']
		text = request.form['text']
		twilio_client.messages.create(to=USER_NUMBER, from_=TWILIO_NUMBER, body=message)


	return Response(), 200


@app.route('/', methods=['GET'])
def test():
	return Response('It works!')

if __name__ == '__main__':
	app.run(debug=True)
