# Steam Library Viewer

A desktop JavaFX application that allows users to view their (or any public) Steam library by entering a custom 
Steam URL or 64-bit SteamID. Users can also export the game list to a plain text format.

- User enters their Steam custom URL (or SteamID64) into the JavaFX app.
- The app makes a request to the backend server hosted on Render.
- The backend resolves the custom URL to a SteamID64 (if necessary) and fetches the user’s owned games via the Steam Web API.
- The game list is sent back to the frontend and displayed in a table view.
- The user can export the list to Text.

## .exe usage

The .exe included in this repo will install a program into `c:/program file/SteamLibraryViewer`
 - windows might detect this as a virus (it isn't)

Locate the `/SteamLibraryViewer/` and double-click the `*.exe`.

With the app opened, you can provide a SteamID64 or Custom URL (just the name) and click `Fetch Library`.
> if you get a server status response of 500, just try fetching again, my host sucks or maybe its my code, its 
> probably my code.

You should be able to export this table of games to a .txt file.


## Back-end Repo
[Back-end](https://github.com/007snoop/steam-backend)

This repo is written in ejs and uses node.
It is hosted on render.com.
This is a free Node host so it may take upwards of 50 seconds for the API to respond. 



> This was made because I googled and a lot of people wanted a way to get their steam library, so here you go.
