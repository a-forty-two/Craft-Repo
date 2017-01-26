# import libraries that are needed
import datetime as DT
from twython import Twython
from nltk.sentiment.vader import SentimentIntensityAnalyzer


# twitter keys and scrapping
today = DT.date.today()
api_key = 'q73e5DORUXRXFQ39aBIyFrOMc'
api_secret = 'yYaZFbT0hGUjkjonjFZrStMz6E7v4CMurHZsxA3zK2LXkvK3Wv'
access_key = '	814646900741832704-UldcU2A3I9cwRixapqdNiDKpYP6K30H'
access_secret = 'luD7hiXxlrf1XetE2aMcQEuFs1sPg4U6pVs94Jki10j68'
twitter = Twython(app_key = api_key, app_secret = api_secret)
query = 'AAPL'

# counter an variable initialization
score_dict = {}
c_ = 0
c = 0
f = 0
f_ = 0
ids = []

for i in range(1,180):
    
    sid = SentimentIntensityAnalyzer()
    
    if i == 1:
        
        tweets = twitter.search(q = query, count = 100, until = today)
        
        for t in tweets['statuses']:
            
            id_i = t['id']
            ids.append(id_i)
            followers = int(t['user']['followers_count'])
            text = str(t['text'].encode('ascii', 'ignore').decode('ascii'))
            
            
            if followers > 100:
                
                score_dict[id_i] = [sid.polarity_scores(text)['compound'], followers]
                f += followers
                c+= 1
            else:
                c_+=1
                f_ += followers
                
        max_id = min(ids)
                            
    else:
        
        tweets = twitter.search(q = query, count = 100, until = today, max_id = max_id)
        
        for t in tweets['statuses']:
            id_i = t['id']
            ids.append(id_i)
            followers = int(t['user']['followers_count'])
            
            if followers > 500:
                score_dict[id_i] = [sid.polarity_scores(text)['compound'], followers]
                f += followers
                c+= 1
            else:
                c_+=1
                f_ += followers
        
        max_id = min(ids)
    
    if i%10 == 0:
        print i, max_id, len(set(ids))
    else:
        continue
    
score_counter = 0       
for key,value in score_dict.items():
      score_counter += (value[0]/value[1]) * (value[1]/followers)

print 'Total Tweets Mined: ', c
print 'Total Followers of Mined Tweets: ', f
print 'Final Sentiment Score Score: ', score_counter





'''
The below code can be used to perform analysis using Watson's Alchemy 
Sentiment analysis api.  This is limited to 1000 calls per day though
and $.007 after, or $7 for 1000 api calls.



watson_key = '4d70e19e006ff6e85391701497f71d35ceec1db6'
watson_usernam = 'craft1.brian@gmail.com'
watson_password = 'Samso74411_'
alchemyapi = imp.load_source('alchemyapi', '/Users/Brian/Desktop/python_web_scapping/alchemyapi.py')
api = alchemyapi.AlchemyAPI()


__pos = 0
__neu = 0
__neg = 0
__exp = 0 

for t in tweets['statuses']:
    
    date = t['created_at']
    followers = int(t['user']['followers_count'])
    text = str(t['text'].encode('ascii', 'ignore').decode('ascii'))
    print text
    
    response = api.sentiment("text", text)['docSentiment']['type']
    print __c, reponse
    __c += 1
    
        if response == 'positive':
            __pos += followers
            
        elif response == 'negative':
            __neg += followers
            
        elif response == 'neutral':
            __neu += followers
        
        else:
            __exp += followers
               
print 'Positive: ', __pos
print 'Negative: ', __neg
print 'Neural: ', __neu
print 'Exceptions: ', __exp
        
'''  
    
import requests
from lxml import html

url = 'https://www.google.com/search?q=appl&oq=appl&aqs=chrome..69i57j0l5.823j0j7&sourceid=chrome&ie=UTF-8#tbm=nws&q=appl+stock'
response = requests.get(url)
pagehtml = html.fromstring(response.text)
news = pagehtml.xpath(".//span[@class='titletext']/text()")

for i in news:
    print i
    print '\n'


dir(response)

response.text

text = str(response.text)
type(text)





tree = ET.fromstring(text)
url_to_news = tree.xpath(".//div[@class='esc-lead-article-title-wrapper']/h2[@class='esc-lead-article-title']/a/@href")
for url in url_to_news:
    print(url)