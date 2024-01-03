import { get } from '@/services/api/index.js'

export async function getCategoryNews(category, page, size) {
  const data = {
    category
  }
  if (page) data['page'] = page
  if (size) data['size'] = size
  return get('news/categoryquery', data)
}

export async function getInformationGapByDate(date) {
  return get('news/datequery', {
    date
  })
}

export async function getInformationGapList() {
  return get('news/information_gap')
}

export async function getSearchNews(key) {
  return get('news/query', {
    key
  })
}