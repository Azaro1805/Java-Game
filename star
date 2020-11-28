import csv
import numpy as np
import pandas as pan
import random
import collections


random.seed (364)

'''def readCsv (fileName):
    with open(fileName, 'r') as csv_file:
        csv_reader = csv.reader(csv_file)
        return csv_reader'''
'''def addRingNum(locations , RingNumber):
    for i in range(len(neighborsRing)):
        if(neighborsRing[i][0] == locations and neighborsRing[i][1] == ""):
            neighborsRing[i][1] = RingNumber
            print("the location :", neighborsRing[i][0] , " , the number of ring after add :", neighborsRing[i][1])'''
'''def myNeighborsSet(locations):
    NeibOfLocations = set()
    for i in range(len(CityDB)):
            #print("i = " , i , " , ", CityDB[i][1],"==",locations)
            if(CityDB[i][0]==locations and CityDB[i][1]!="" ):
                #print("add" , CityDB[i][1])
                NeibOfLocations.add(CityDB[i][1])
    return NeibOfLocations'''
'''def makeRing(locations , RingNumber):
    NeibOfLocations = myNeighborsSet(locations)
    print("neibset of  = ", locations, " , is: " , NeibOfLocations)
    for i in NeibOfLocations:
        addRingNum(i , RingNumber)'''
'''
def makeEndLocationRing(endLocation):
    addRingNum(endLocation, "0")
    makeRing(endLocation, "1")

def add1tostr(string):
    string = int(string) + 1
    string = str(string)
    return string

def recorRun (startLocation, endLocation):
    flag = True
    makeEndLocationRing(endLocation)
    RingNumber = "0"

    print("neighborsRing = " , np.matrix(neighborsRing) )
    print("###################################### Start while hyoristic : #######################################")
    print()
    while (flag):
        RingNumber = add1tostr(RingNumber)
        print("flag = ", flag , ", RingNumber = " , RingNumber)
        for i in range(len(neighborsRing)):
            if (flag == False):
                break
            #print("neighborsRing[i][1] =" , neighborsRing[i][1] , "RingNumber = " , RingNumber)
            if(neighborsRing[i][1] == RingNumber):
                #print("neibSet befor = ", myNeighborsSet(neighborsRing[i][0]))
                #neibSet = myNeighborsSet(neighborsRing[i][0])
                RingNumber2 = add1tostr(RingNumber)
                print("neighborsRing = ", np.matrix(neighborsRing))
                print("###################################### new firends of the ring  : #######################################")
                print("name  = ", neighborsRing[i][0] , ", his RingNumber = ", neighborsRing[i][1] ,", new ring =",RingNumber2)
                print()
                makeRing(neighborsRing[i][0], RingNumber2)
                if (neighborsRing[i][0] == startLocation):
                    print("%%%%%%%%%%%%%%%%%%%%%%%%%% END %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%")
                    print("i = startLocation =", neighborsRing[i][0])
                    flag = False
                    break
    print("neighborsRing = ", np.matrix(neighborsRing))

def hiyo (startLocation, endLocation):
    צריך להכניס לסוף טבעת 0
    recorRun(startLocation, endLocation)'''

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

def BFS(startLocation, endLocation):
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

def hiyoCity(startLocation, endLocation):
    BFS(startLocation, endLocation)

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

def find_path(starting_locations, goal_locations, search_method, detail_output):
    split = starting_locations.split(sep=", ", maxsplit=2)
    country1 = split[1]
    split = goal_locations.split(sep=", ", maxsplit=2)
    country2 = split[1]

    hiyoCity(country1, country2)

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


        '''for i in fortier:
            if(i.name ==  "Elmore County, AL"):
                i.dataF = i.dataF+1
                print(i.dataF)'''

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

    '''pathList.append(Node(starting_locations ,getHeuristicValue(starting_locations),myNeighborsSet(starting_locations) ,"none"))
    printlist(pathList)
    f = + 1 + getHeuristicValue(starting_locations)'''


    #while (getPath == False):

        #pathList.append(Node(starting_locations, 0, myNeighborsSet(starting_locations), "none"))
        #f =  + 1 + getHeuristicValue(starting_locations)



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
#path = ["" for i in range(len(countryDB))]
pathList = list()
frontier = list()
find_path("Washington County, UT","San Diego County, CA","A*",True)
find_path("Chicot County, AR","Bienville Parish, LA","A*",True)
find_path("Fairfield County, CT","Rensselaer County, NY","A*",True)

print()
print("End the Algorithm")




#print(np.matrix(countryDB))
#hiyoCity("AL","NC")
'''ans=getHeuristicValue("Autauga County, AL")
print(ans)'''


'''CityDB[neighborname][countyname]'''
#print(CityDB[0][0])
#print(np.matrix(CityDB))
'''

neighborsRing = neighborsRingArray()

print("neighborsRing = ", np.matrix(neighborsRing))

#for i in range(len(neighborsRing)):
#    print(i)
#    print("name  = " , neighborsRing[i][0])
#    print("set =  ", myNeighborsSet(neighborsRing[i][0]))


#test("Autauga County, AL" , "Shelby County, AL")
start = "Autauga County, AL"
end = "Russell County, KY"
hiyo(start , end)

print("********************* finish *****************")
for i in neighborsRing:
    #if(i[1]>="0"):
     #   print(" ring ", i[0], " County is = ", i[1])
    if (i[0]=="Autauga County, AL"):
        print(" ring ", start," County is = " ,i[1])
    if (i[0]==end):
        print(" ring ", end," County is = " ,i[1])
'''


'''neighbors'''
