import feedparser
from nltk.sentiment.vader import SentimentIntensityAnalyzer


url = 'https://www.google.com/search?hl=en&gl=us&tbm=nws&authuser=0&q=aapl&oq=aapl&gs_l=news-cc.3..43j0l9j43i53.1189.3115.0.3292.8.5.2.1.1.0.205.633.1j2j1.4.0...0.0...1ac.1.RKJ1ItRkTd0'
d = feedparser.parse(url)
summary = str(d['feed']['summary'].encode('ascii', 'ignore').decode('ascii'))
start = '<div class="st">'
end = '</div>'
lst = summary.split('div')
sid = SentimentIntensityAnalyzer()
counter_score = 0
counter_articles = 0
for i in lst:
    if 'class="st">' in i:
        i = i.replace('class="st">' ,'')
        i = i.replace('<b>','')
        i = i.replace('...','')
        i = i.replace('</b>','')
        i = i.replace('</','')
        print i
        print sid.polarity_scores(i)['compound']
        print '\n'
        counter_score += sid.polarity_scores(i)['compound']
        counter_articles += 1
    else:
        continue

print "Average Compound Score: ", counter_score/counter_articles

