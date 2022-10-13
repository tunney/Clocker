# Clocker

A utility that can be used to programmatically perform a clocking operation in MYTIME
Requires Java, assumes MacOS

Outstanding:
* Query inclusion of randomisation around clocking in and out, there but left out, needs testing with crontab
* Addition of Calendar integration for public holidays and holidays

Usage:
Edit clock.sh to include the location of the compiled clocker class, your username, and password
chmod 777 clock.sh

At a terminal "crontab -e"

Example crontab:

00 09 * * 1-5 /Users/username/dev/clock.sh >/tmp/stdout.log 2>/tmp/stderr.log
30 17 * * 1-5 /Users/username/dev/clock.sh >/tmp/stdout.log 2>/tmp/stderr.log
