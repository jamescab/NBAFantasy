# NBAFantasy

##Description
This is a simplified simulator for an NBA fantasy season. A number of fantasy owners can be determined and enter in a drafting process to set teams. After all teams are finalized, the fantasy season begins. During this period, owners are able to scout players to add to their team and add and drop them at their wish. Player fantasy points and health statuses are updated every fantasy "week". Player information is taken from actual player statistics after parsing NBA JSON files. The backend of the application was implemented with Java and the frontend was implemented using Java's Swing GUI. Unit tests for the backend are done with JUnit.

##App Details
The NBAFantasy Simulator offers the following functionality:
* Set number of owners before season starts
* Conduct a drafting process for owners to pick players for their team
* Player points accumulated throughout each week based on performances and applied to respective team points for the week
* Owners can add and drop players
* Owners can search for players
* Player status updated each week. Owners notified if player is injured or unable to play
