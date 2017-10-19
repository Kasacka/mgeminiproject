# Miniprojekt-Server Heroku

Installiert mit download der Dateien und Anpassung von package.json sowie git Repo initialiseren und committen. Danach mit folgenden Befehlen auf Heroku deployen:

    sudo heroku login
    sudo heroku create
    git push heroku master
    sudo heroku ps:scale web=1
    sudo heroku open

Heroku Server ist erreichbar unter warm-ocean-14675 mit

    https://warm-ocean-14675.herokuapp.com/
    https://git.heroku.com/warm-ocean-14675.git