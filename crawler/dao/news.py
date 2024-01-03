import logging
from typing import List, Set

from pymongo import MongoClient
from pymongo.collection import Collection
from pymongo.database import Database

from crawler.entity.news import News
from crawler.entity.source import Source


class NewsDao:
    def __init__(self):
        self.client: MongoClient = MongoClient(host='kafka', port=27017)
        self.db: Database = self.client['test']
        self.collection: Collection = self.db['news']

    def insert_news(self, news: News):
        result = self.collection.insert_one(news.__dict__)

    def insert_news_list(self, news_list: List[News]):
        result = self.collection.insert_many([news.__dict__ for news in news_list])

    def get_news_link_set(self, source: Source) -> Set[str]:
        news_list = self.collection.find({'source': source.value}, {'link': 1})
        ids = set()
        for news in news_list:
            ids.add(news.get('link'))
        return ids


if __name__ == '__main__':
    nd: NewsDao = NewsDao()

    print(nd.get_news_link_set(Source.KE_PU_CHINA))

    # time = datetime.strptime('2023-12-27 08:19', "%Y-%m-%d %H:%M")
    # timestamp = int(time.timestamp())
    # news = News(
    #     title="AI+科研：中国科学事业的长河上升起的一道曙光！",
    #     description='面向中国产学智能化，中科曙光带来了什么？',
    #     source=Source.KE_PU_CHINA,
    #     category=Category.TECHNOLOGY,
    #     link='https://blog.51cto.com/u_16175507/7581947',
    #     publish_date=time.strftime('%Y-%m-%d'),
    #     publish_timestamp=timestamp,
    #     keyword_list=["AI", "人工智能", "中科曙光", "计算科学"],
    #     views=0
    # )
    # nd.insert_news(news)
