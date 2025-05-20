# Steam Library Viewer

A desktop JavaFX application that allows users to view their (or any public) Steam library by entering a custom Steam URL or 64-bit SteamID. Users can also export the game list to CSV, Excel, or plain text formats.

- User enters their Steam custom URL (or SteamID64) into the JavaFX app.
- The app makes a request to the backend server hosted on Render.
- The backend resolves the custom URL to a SteamID64 (if necessary) and fetches the user’s owned games via the Steam Web API.
- The game list is sent back to the frontend and displayed in a table view.
- (TODO) The user can export the list to CSV/Excel/Text.

> This was made because I googled and a lot of people wanted a way to get their steam library, so here you go.
