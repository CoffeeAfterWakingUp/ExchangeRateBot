# ExchangeRateBot
This bot helps you to obtain a history of changes in the exchange rate (USD, EUR, RUB) against tenge for 10 days. 

I used webhook way to receive messages from the Telegram servers. 
This application was deployed on heroku.

In case of bot would not work, then you have to:
1. Clone this project
2. You have to have tools that enable to expose a server to the Internet. I used [ngrok](https://ngrok.com/download)
3. Change telegrambot.webHookPath with your server in the [application.
properties](https://github.com/CoffeeAfterWakingUp/ExchangeRateBot/blob/a65991c5ddb02d6a5243aefa771d1651d60ce3df/src/main/resources/application.properties)
4. Set your server https://api.telegram.org/bot5333342898:AAGhuVGdiEahf7vPZ0nydXeHuqDOIQ9tRs0/setWebHook?url={your server}

If you have question, feel free to ask: 
* my telegram https://t.me/olzhassyrbek
* email syrbekolzhas@gmail.com
