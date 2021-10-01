# MovieKnight
Android app which provides now playing and coming soon movies to theaters

It consumes IMDB public Api -> https://imdb-api.com/

It uses MVVM pattern with HILT for DI and Coroutines for async api calls :D

And ROOM to save movies locally for 1 hour :D you can always swipe to refresh to force the api call 😉

I also use a visual component -> RecyclerComponent from my own components library https://github.com/fede118/components

My current TODO list:

- handle HTTP and Coroutine Errors (400, 500, corrupt data) -> WIP

- Add tests :O

- Add Firebase Crash analitics

- Add Detail screen when a movie is Touched

- Add DataBinding in the carousel component

- Add Cache using CacheDir
