### Websockets
`brew install websocat` (or download windows and add to path) and then `websocat -s 1234` to host a websocket server on port 1234. Type a message to see the server properly listens

`websocat ws://127.0.0.1:1235/` can be used to listen / test the websocket reporter

