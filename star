import csv
import numpy as np
import pandas as pan
import random
import copy

import collections


random.seed (364)

def createDB (data):
    CityDB = np.array(data)
    ''' CityDB2 = [ [ "" for j in range(2) ] for i in range(len(CityDB)) ]

    for i in range(len(CityDB)):
        for j in range(len(CityDB[i])):
            if(CityDB[i][0] != CityDB[i][1]):
                CityDB2[i][j] = CityDB[i][j]'''
    return CityDB

def createCountryDB ():
    CountryDB = [[set() for j in range(2)] for i in range(56)]
    k = 0
    for i in (CityDB):
        alreadyInside = False
        Country = i[0].split(sep=", ", maxsplit=2)
        for j in range(len(CountryDB)):
            Country2 = getVauleFromSet(CountryDB[j][0])
            if (Country2 == Country[1]):
                alreadyInside = True
                break
        if (alreadyInside == False):
            CountryDB[k][0].add(Country[1])
            for n in CityDB:
                alreadyInside2 = False
                fromCountry = n[0].split(sep=", ", maxsplit=2)
                toCountry = n[1].split(sep=", ", maxsplit=2)
                if (Country[1] == fromCountry[1]):
                    if(Country[1] == toCountry[1]):
                        alreadyInside2 = True
                    for t in CountryDB[k][1]:
                        if (toCountry[1] == t):
                            alreadyInside2 = True
                            break
                    if (alreadyInside2 == False):
                        CountryDB[k][1].add(toCountry[1])
            k = k + 1
    return CountryDB

def neighborsRingArray():
    neibSet = set()

    for i in range(len(CityDB)):
        if i not in neibSet:
            neibSet.add(CityDB[i][0])

    neibArray = [ [ "" for j in range(2) ] for i in range(len(neibSet)-1) ]

    j=-1
    for i in (neibSet):
        neibArray[j][0] = i
        j = j+1

    neibSet.clear()
    return neibArray

def getVauleFromSet(set1):
    if(len(set1)==0):
        return " "
    for e in set1:
        return e

def addRingNum(locations , RingNumber):
    for i in range(len(countryDB)):
        city = getVauleFromSet(countryDB[i][0])
        #print("city = " , city , ", locations = " , locations, ringNumbers[i] , len(countryDB)+5)
        if(city == locations and ringNumbers[i]==len(countryDB)+5):
            citiesName[i] = city
            ringNumbers[i] = RingNumber
            #print("the location :", citiesName[i] , " , the number of ring after add :", ringNumbers[i])

def myNeighborsSet(locations):
    for i in range(len(countryDB)):
        city = getVauleFromSet(countryDB[i][0])
        if(city==locations):
            return countryDB[i][1]

def makeRing(locations , ringNumber):
    NeibOfLocations = myNeighborsSet(locations)
    #print("neibset of  = ", locations, " , is: " , NeibOfLocations)
    for i in NeibOfLocations:
        addRingNum(i , ringNumber)

def inFirstRing(endLocation):
    for i in range (len(citiesName)):
        if(citiesName[i]==endLocation):
            if(ringNumbers[i]==1):
                return True
            if (ringNumbers[i] == 0):
                return True

    return False

def StartPointRing(startLocation, endLocation):
    ringNumber = 0
    for i in range (len(countryDB)):
        country = getVauleFromSet(countryDB[i][0])
        if(country==endLocation):
            ringNumbers[i] = 0
            citiesName[i] = endLocation
            #print("the location : ", citiesName[i], ", the number of ring after add : ", ringNumbers[i])
    makeRing(endLocation, ringNumber + 1)

def BFSCity(startLocation, endLocation):
    arriveToEnd = False
    ringNumber = 0
    for i in range(len(ringNumbers)):
        ringNumbers[i] = len(countryDB) + 5
        citiesName[i] = ""
    StartPointRing(startLocation, endLocation)
    #print(citiesName)
    #print(ringNumbers)
    arriveToEnd=inFirstRing(startLocation)
    #print(arriveToEnd)
    while (arriveToEnd == False):
        ringNumber = ringNumber + 1
        #print("flag = ", arriveToEnd, ", RingNumber = ", ringNumber)
        for i in range(len(countryDB)):
            if (arriveToEnd == True):
                break
            # print("countryDB[i][0] =" , countryDB[i][0] , ", ringNumber = " , ringNumbers[i])
            if (ringNumbers[i] == ringNumber):
                # print("neibSet befor = ", myNeighborsSet(neighborsRing[i][0]))
                # neibSet = myNeighborsSet(neighborsRing[i][0])
                ringNumber2 = ringNumber + 1
                # print("neighborsRing = ", np.matrix(neighborsRing))
                #print("###################################### new firends of the ring  : #######################################")
                #print(" name  = ", countryDB[i][0], "RingNumber = ", ringNumbers[i], ", new ring =", ringNumber2)
                city2 = getVauleFromSet(countryDB[i][0])
                makeRing(city2, ringNumber2)
                if (city2 == startLocation):
                    #print("%%%%%%%%%%%%%%%%%%%%%%%%%% END %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                    #print("i = endLocation =", city2)
                    arriveToEnd = True
                    break
    #print("neighborsRing = ", citiesName)
    #print("neighborsRing = ", ringNumbers)

def findPath2(startLocation, endLocation):
    print()
    path3 = ["" for i in range(len(countryDB))]
    getPath = False

    while (getPath==False):
        #print("while")
        for i in range(len(citiesName)):
            city = getVauleFromSet(countryDB[i][0])
            if (city == endLocation):
                ring = ringNumbers[i]
                k = ringNumbers[i]
                #print(" k = ", k)
                k = k - 1
        while(getPath == False):
            i=0
            city = getVauleFromSet(countryDB[i][0])
            #print("city = " , city, ", ringNumbers[i]= ", ringNumbers[i] , ", ring  = " , ring-1 , ", nieb = ", countryDB[i][1] , ", path3[k] = ", path3[k+1])
            if ( (ringNumbers[i] == ring - 1) and (path3[k+1] in countryDB[i][1])):
                #print("add to path " , city , ", his ring = " ,ring-1)
                path3[k] = city
                k = k-1
                ring = ring - 1
                #print("path3 = " , path3)
                i=0
                if (k == 0 and city == startLocation ):
                    getPath = True
                    break
            i=i+1
            #print(getPath)
            '''if (i== (len(countryDB)-1)):
                path3.clear()'''
            i = i + 1

def getHeuristicValue(location):
    locationSplit = location.split(sep=", ", maxsplit=2)
    city = locationSplit[0]
    Country = locationSplit[1]
    #print("Country = ", Country ,", City = ", city)
    for i in range (len(citiesName)):
        Country2 = getVauleFromSet(countryDB[i][0])
        #print("Country2 = ",Country2, " , Country=" , Country)
        if(Country2 == Country):
            return ringNumbers[i]
    return 61

def printlist (list):
    for i in list:
        print("i.name = ", i.name, ", i.dataF = ", i.dataF, ", i.next = ", i.next, ", i.before = ", i.before)

def myNeighborsSetCity (location):
    set1 = set()
    for i in (CityDB):
        fromCountry = i[0].split(sep=", ", maxsplit=2)
        toCountry = i[1].split(sep=", ", maxsplit=2)
        city = fromCountry[0]+", "+fromCountry[1]
        city2 = toCountry[0]+", "+toCountry[1]
        if (city == location and city2!= location):
            set1.add(city2)
    return set1

def firstStep(starting_locations):
    set = myNeighborsSetCity(starting_locations)
    pathList.append(Node(starting_locations,getHeuristicValue(starting_locations),set,"Start Point"))
    #print()
    #print(set)
    for i in set:
        frontier.append(Node(i, getHeuristicValue(i) + 0.001, myNeighborsSetCity(i), starting_locations))
    #print("frontier after first step")
    #printlist(frontier)
    #print("path after first step")
    #printlist(pathList)
    #print()

def getTheLowest ():
    min = 65
    counter = 1
    name = ""
    for i in frontier:
        if (counter==1):
            min = i.dataF
            name = i
        if(min>i.dataF):
            min = i.dataF
            name = i
        counter = counter+1
    return name

def isInList (list , location):
    for i in list:
        if (i.name == location):
            return True
    return False

def allreadyInsideFrontier(me,friend):
    for i in frontier:
        if (i.name == friend):
            #print(i.name , " , i.DatafF = " , i.dataF, " , i.before = " , i.before)
            if (getHeuristicValue(me.name) == getHeuristicValue(friend)):
                if (i.dataF > (me.dataF + 0.001)):
                    i.dataF = me.dataF + 0.001
                    i.before = me.name
            else:
                newF = (me.dataF - getHeuristicValue(me.name)) + getHeuristicValue(friend) + 0.001
                if (i.dataF > newF):
                    i.dataF = newF
                    i.before = me.name
            #print(i.name , " , i.DatafF = " , i.dataF, " , i.before = " , i.before)
            return True
    return False

def caculateF (me,friend):
    meName = me.name
    if(allreadyInsideFrontier(me,friend) == False):
        if(getHeuristicValue(me.name)==getHeuristicValue(friend)):
            return me.dataF + 0.001
        return ( me.dataF-getHeuristicValue(me.name) )+ getHeuristicValue(friend) + 0.001
    return True

def sortNodeList(nodeList):
    #printlist(nodeList)
    #print()
    sorted = list()
    max = 0
    max2 = 0
    finish =False
    while (finish == False):
        max = 0
        max2 = 0
        for i in nodeList:
            if (i.dataF > max):
                max = i.dataF
                max2 = i.dataF
            if (i.dataF < max and (max- i.dataF)<0.2):
                if(i.dataF<max2):
                    max2= i.dataF

        #print("max = " , max , " , max2 = " , max2)
        for j in nodeList:
            if (j.dataF == max2):
                sorted.append(j)
                nodeList.remove(j)
                if(len(nodeList)==0):
                    finish = True
                    break


    #printlist(sorted)
    return sorted

def printRouteString(pathList):
    stringPath = ""
    for i in pathList:

        stringPath = stringPath + i.name + " -> "

    stringPath = stringPath[0:len(stringPath)-4]
    print(stringPath)

def printPath(endLocation):
    path = list()
    Finish = False
    lastLocation = ""
    endLocationInside=False
    while (Finish == False):
        for i in pathList :
            if(endLocation == i.name and endLocationInside==False):
                #print("End location")
                #print(i.name, " , i.DatafF = ", i.dataF, " , i.before = ", i.before)
                path.append(i)
                lastLocation = i.before
                endLocationInside = True
                #print("lastLocation = ", lastLocation)
            if (lastLocation == i.name ):
                #print(i.name, " , i.DatafF = ", i.dataF, " , i.before = ", i.before)
                path.append(i)
                lastLocation = i.before
                #print("lastLocation = ", lastLocation)
                if (lastLocation == "Start Point"):
                    Finish = True
                    break
    path=sortNodeList(path)
    printRouteString(path)

    for i in path:
        pathSorted.append(i)

def createOutPutPrint():
    print("A")

def find_path_for_each_country(starting_locations, goal_locations):

    split = starting_locations.split(sep=", ", maxsplit=2)
    country1 = split[1]
    split = goal_locations.split(sep=", ", maxsplit=2)
    country2 = split[1]

    BFSCity(country1, country2)

    firstStep(starting_locations)
    getPath = False

    while (getPath == False):
        if (isInList(pathList, goal_locations) == True):
            getPath = True
            #print("##################### FIND PATH ####################")
            printPath(goal_locations)
            #print(" frontier")
            #printlist(frontier)
            break
        #print()
        lowest = getTheLowest()
        #print("the lowest name  = " ,lowest.name , ", data  = ",lowest.dataF,", before  = ", lowest.before,", set  = ", lowest.next)
        pathList.append(lowest)
        #print("pathList = ")
        #printlist(pathList)
        frontier.remove(lowest)

        for i in lowest.next:
            if (isInList(pathList,i) == False):
                F = caculateF(lowest, i)
                if(F != True):
                    frontier.append(Node(i, F, myNeighborsSetCity(i), lowest.name))
            #print()
            #print("next i = ",  i)
            #printlist(fortier)
        #print("Frontier = ")
        #printlist(frontier)
        #print()

def printOutput(array):
    print()
    max = len(array[0])
    for i in array:
        if(max < len(i)):
            max = len(i)

    pathArray = [["" for j in range (len(array))] for i in range(max)]
    for j in range(len(pathArray[0])):
        list1 = array[j]
        for i in range (len(pathArray)):
            if (len(array[j])-1==i):
                pathArray[i][j] = list1[i].name
            if(len(array[j])-1>i):
                pathArray[i][j] = list1[i].name
            if (pathArray[i][j]==""):
                pathArray[i][j]=pathArray[i-1][j]

    output = ["" for i in range (len(pathArray))]
    for i in range(len(output)):
        output[i] = "{"
        for j in range(len(pathArray[0])):
            output[i]  = output[i] + pathArray[i][j] + " ; "
        output[i] = output[i][0:(len(output[i])-3)]+"}"
        print(output[i])

def find_path(starting_locations, goal_locations, search_method, detail_output):
    arrayofPathlist = [list() for i in range(len(starting_locations))]
    for i in range(len(starting_locations)):
        find_path_for_each_country(starting_locations[i],goal_locations[i])
        arrayofPathlist[i]=copy.deepcopy(pathSorted)
        pathSorted.clear()
        pathList.clear()
        frontier.clear()
        #printlist(arrayofPathlist[i])
        #print()
    printOutput(arrayofPathlist)

    '''if (detail_output):
           pathFound = False
           path = starting_locations + " -> "


           if(pathFound):
               print(path)
           else:
               print("No path found.")
        else:
            print("If the binary detail_output variable is true,Print out the heuristic value of the the state of your "
                  "locations after the First transformation.")
        '''
class Node:
    def __init__(self,name, data , next, before):
        self.name = name
        self.dataF = data
        self.next = next
        self.before = before

print()
print("Start the Algorithm")
print()

data = pan.read_csv(r'C:\Users\oraza\Downloads\adjacency.csv')
CityDB = createDB(data)
#print(np.matrix(CityDB))
countryDB = createCountryDB()
ringNumbers = [len(countryDB) + 5 for i in range(len(countryDB))]
citiesName = ["" for i in range(len(countryDB))]
pathList = list()
pathSorted = list()
frontier = list()
startList = [" " for i in range(3)]
endList = [" " for i in range(3)]

startList[0]="Washington County, UT"
startList[1]="Chicot County, AR"
startList[2]="Fairfield County, CT"

endList[0]="San Diego County, CA"
endList[1]="Bienville Parish, LA"
endList[2]="Rensselaer County, NY"

'''startList = list()
endList = list()

startList.append("Washington County, UT")
startList.append("Chicot County, AR")
startList.append("Fairfield County, CT")

endList.append("San Diego County, CA")
endList.append("Bienville Parish, LA")
endList.append("Rensselaer County, NY")'''

find_path(startList, endList, "A*", True)

print()
print("End the Algorithm")
