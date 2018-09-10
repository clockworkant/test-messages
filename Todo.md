Requirements:
====
1. The `data.json` file is to be read only once during the first app run. After that all data should be kept in a local database.
2. ~~For messages the avatar of the user, name of the user, content of the message and attachments will be displayed. All attachments are displayed one below another with first attachment displayed below its message.~~
3. ~~Messages are loaded from the local database by portions (20 messages at once). Loading of the next page should be triggered automatically when the user scrolls to the end of current page.~~
    3.1 need to use a local database
4. ~~Messages from user with id=1 should be aligned to the right, without the user name and avatar (to distinguish session user in UI).~~
5. ~~Messages from other users should be aligned to the left with avatar and name.~~
6. Each message has a “Delete message” action that removes this message from UI and local database. It's up to you to decide how this action will be triggered.
7. Each attachment has a “Delete attachment” action that removes only this attachment from UI and local database.
8. `RecyclerView` should work in “stable ids mode” with `DefaultItemAnimator` working properly.
~~9. `minSdkVersion=21`.~~

Todo
===
- Rename package to correct spelling of ;messenger
- Build graphical UI to without data
    - Separate user 0 rows from other users
        - ~~Avatar for non 0 user~~
        - Circle image view?
        - ~~Card interface~~
        - ~~attachments~~
- fetch items on background thread
- Delete action - remove item from db and from list. Probably swipe  
- ~~pagination - load more when at bottom of recyclerview using on scroll listener~~
    - ~~get pages by delta - get items after last id~~
- ~~in memory naive database backed by json~~
- real database using room or something

Issues/conflicting details
===
The image shows content scrolling from the bottom, leaving a gap at the top. Item 3 suggests scrolling to the bottom to load more content. 


