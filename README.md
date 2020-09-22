

# API

- Get All Review (10)
 >**GET** http://localhost:5555/reviews/all

- Search by keywords
  >**GET**  http://localhost:5555/reviews/search?query=example

- Search by id
  >**GET**  http://localhost:5555/reviews/number

- Update Review
  >**PUT** http://localhost:5555/reviews/number
  > ```
        "body": {
					"mode": "raw",
					"raw": "{\n    \"review\":\"gggg\"\n}",
					"options": {
						"raw": {
							"language": "json"
						}
					}
				}