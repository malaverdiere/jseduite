%% Logic Facts describing jSeduite architecture
%% author Sebastien Mosser <mosser@polytech.unice.fr>

%%%%%%%%%%%%%%%%%%%
%%% Description %%%
%%%%%%%%%%%%%%%%%%%

%% library(X) :=: X is a library
:- multifile library/1.

%% depends(X,Y) :=: library(X), library(Y), X uses a method defined in Y
:- multifile depends/2.

%% table(X) :=: X is a table inside the jSeduite database
:- multifile table/1.

%% references(A,B) :=: table(A), table(B) and A defines a reference to B
:- multifile references/2.

%% view(X) :=: X is a view inside tje jSeduite database (view \in table)
:- multifile view/1.

%% webservice(X) :=: X is defines as a black box web service inside jSeduite
:- multifile webservice/1.

%% provides(X,O) :=: webservice(X) and X provides an operation named O
:- multifile provides/2.

%% uses(X,Y) :=: webservice(X), table(Y) and X retrieve data from Y
:- multifile uses/2.

%% loads(X,Y) :=: webservice(X), library(Y), X use functionalities from Y.
:- multifile loads/2.

%% orchestration(X) :=: X is an orchestration (orchestration \in webservice)
:- multifile orchestration/1.

%% usesAsPartner(X,Y) :=: orchestration(X), webservice(Y), Y is a partner of X
:- multifile usesAsPartner/2.

%%%%%%%%%%%%%%%%%
%%% Libraries %%%
%%%%%%%%%%%%%%%%%

library(databaseConnection).
library(iCal4J).
library(rome).
library(jdom).
depends(rome,jdom).

%%%%%%%%%%%%%%%%
%%% Database %%%
%%%%%%%%%%%%%%%%

% From BreakManager.sql
table(break_time).
references(break_time, promos).
table(break_time_days_lnk).
references(break_time_days_lnk, break_time).
view(break_time_today).
references(break_time_today, break_time).
references(break_time_today, promos).
references(break_time_today, break_time_days_lnk).

% From BreakingNews.sql
table(breaking_news).

% From DataCache.sql
table(cache).

% From ErrorLogger.sql
table(errors).

% From FeedRegistry.sql
table(feed_class).
table(feed_registry).
references(feed_registry, feed_class).

% From InternalNews.sql
table(internal_news_target).
table(internal_news).
references(internal_news,internal_news_target).
view(current_internal_news).
references(current_internal_news, internal_news).
references(current_internal_news, internal_news_target).

% From PartnersKey.sql
table(partners_key).

% From PicturealbumRegistry.sql
table(picture_album_registry).

% From SchoolLife.sql
table(summon_levels).
table(promos).
table(summonings).
references(summonings, promos).
references(summonings, summon_levels).
view(valid_summonings).
references(valid_summonings, summonings).
references(valid_summonings, summon_levels).
references(valid_summonings, promos).
table(teacher_absences).

% From SettingsManager.sql
table(accounts).
table(sources).
table(preferences).
references(preferences, accounts).
references(preferences, sources).
table(messages).
references(messages, preferences).
view(settings).
references(settings, accounts).
references(settings, sources).
references(settings, preferences).
references(settings, messages).

%%%%%%%%%%%%%%%%%%%%
%%% Web Services %%%
%%%%%%%%%%%%%%%%%%%%

webservice(breakingNews).
provides(breakingNews,getBreakingNews).
uses(breakingNews, breaking_news).
loads(breakingNews, databaseConnection).

webservice(breakTimeManager).
provides(breakTimeManager, getAllBreakTime).
provides(breakTimeManager, getBreakTimeForToday).
provides(breakTimeManager, getBreakTime).
uses(breakTimeManager, break_time).
uses(breakTimeManager, promos).
uses(breakTimeManager, break_time_days_lnk).
uses(breakTimeManager, break_time_today).
loads(breakTimeManager, databaseConnection).

webservice(dataCache).
provides(dataCache, initContent).
provides(dataCache, getContent).
provides(dataCache, isValid).
provides(dataCache, setContent).
uses(dataCache, cache).
loads(dataCache, databaseConnection).

webservice(errorLogger).
provides(errorLogger, log).
provides(errorLogger, getLogs).
uses(errorLogger, errors).
loads(errorLogger, databaseConnection).

webservice(feedRegistry).
provides(feedRegistry, getURL).
provides(feedRegistry, getCategories).
provides(feedRegistry, getNicknames).
uses(feedRegistry, feed_class).
uses(feedRegistry, feed_registry).
loads(feedRegistry, databaseConnection).

webservice(flickrWrapper).
provides(flickrWrapper, getAlbumContent).
provides(flickrWrapper, getFolksonomyContent).
loads(flickrWrapper, jdom).

webservice(iCalReader).
provides(iCalReader, getContent).
provides(iCalReader, getToday).
provides(iCalReader, getNow).
loads(iCalReader,iCal4J).

webservice(internalNews).
provides(internalNews, getTargets).
provides(internalNews, getAllNews).
provides(internalNews, getTargetedNews).
uses(internalNews, internal_news_target).
uses(internalNews, current_internal_news).
loads(internalNews, databaseConnection).

webservice(partnerKeys).
loads(partnerKeys, databaseConnection).
provides(partnerKeys, get).
uses(partnerKeys, partners_key).

webservice(picasaWrapper).
provides(picasaWrapper, getAlbumContent).
provides(picasaWrapper, getFolksonomyContent).
loads(picasaWrapper, jdom).

webservice(pictureAlbumRegistry).
loads(pictureAlbumRegistry, databaseConnection).
provides(pictureAlbumRegistry, getValidAlbums).
uses(pictureAlbumRegistry, picture_album_registry).

webservice(pictureSet).
provides(pictureSet, merge).
provides(pictureSet, truncate).
provides(pictureSet, shuffle).

webservice(rssReader).
provides(rssReader,read).
loads(rssReader, rome).

webservice(studentSummon).
loads(studentSummon, databaseConnection).
provides(studentSummon, getSummoned).
provides(studentSummon, getSummonedByCode).
uses(studentSummon, valid_summonings).

webservice(teacherAbsences).
provides(teacherAbsences, getAbsences).
loads(teacherAbsences, databaseConnection).
uses(teacherAbsences,teacher_absences).

webservice(timeMachine).
provides(timeMachine, compare).

webservice(tvHelper).
provides(tvHelper, extract).

webservice(preferenceManager).
loads(preferenceManager, databaseConnection).
provides(preferenceManager, shouldCall).
provides(preferenceManager, getValue).
provides(preferenceManager, getCallIdentifiers).
provides(preferenceManager, getValueBycallId).
uses(preferenceManager, settings).

webservice(accountManager).
loads(accountManager, databaseConnection).
provides(accountManager, isAuthorized).
uses(accountManager, accounts).

webservice(weatherBugAPI).
provides(weatherBugAPI, getLiveWeatherByCityCode).
provides(weatherBugAPI, getForecastByCityCode).

webservice(helloWorld).
provides(helloWorld,sayHello).
provides(helloWorld, sayPersonalizedHello).

%%%%%%%%%%%%%%%%%%%%%%
%%% Orchestrations %%%
%%%%%%%%%%%%%%%%%%%%%%

orchestration(feedReader).
usesAsPartner(feedReader,feedRegistry).
usesAsPartner(feedReader,rssReader).

orchestration(cachedFeedReader).
usesAsPartner(cachedFeedReader, feedReader).
usesAsPartner(cachedFeedReader, dataCache).

orchestration(imageScraper).
usesAsPartner(imageScraper, flickrWrapper).
usesAsPartner(imageScraper, picasaWrapper).
usesAsPartner(imageScraper, pictureSet).

orchestration(pictureAlbums).
usesAsPartner(pictureAlbums, flickrWrapper).
usesAsPartner(pictureAlbums, picasaWrapper).
usesAsPartner(pictureAlbums, pictureSet).
usesAsPartner(pictureAlbums, pictureAlbumRegistry).

orchestration(tvShows).
usesAsPartner(tvShows, tvHelper).
usesAsPartner(tvShows, cachedFeedReader).

orchestration(weatherProxy).
usesAsPartner(weatherProxy, weatherBugAPI).

%%%%%%%%%%%%%%%
%%% Sources %%%
%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%
%%% Providers %%%
%%%%%%%%%%%%%%%%%
