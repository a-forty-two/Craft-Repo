# import libraries that are needed
import datetime as DT
from twython import Twython
from nltk.sentiment.vader import SentimentIntensityAnalyzer


# twitter keys and scrapping
today = DT.date.today()
api_key = '2zMOV8mZBnt6lgBLKtbujweEb'
api_secret = 'MPQkdfk0N1069ytohZqFmhXB2qJ10lt6L2XmvWvC9EUsHb5zWu'
access_key = '	814646900741832704-qzzmLHMlVrN7QldhuJRhR4aGacYJGb0'
access_secret = 'dgy1Mf0jfoFQoRYUBxgTwVQm43g7GUi3jXNkRZqj68mdk'
twitter = Twython(app_key = api_key, app_secret = api_secret)
query = 'apple stock'

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