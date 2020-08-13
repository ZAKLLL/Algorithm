class Solution:
    def palindromePairs(self, words: List[str]) -> List[List[int]]:
        def palindromeCnt(s1,s2):
            str1=s1+s2
            i,j=0,len(str1)-1
            while i<j and str1[i]==str1[j]:
                i+=1
                j-=1
            if len(str1) %2==0:
                if i>j:
                    return True
            else:
                if i==j:
                    return True
            return False
        ret=[]
        for i in range(len(words)):
            for j in range(i+1,len(words)):
                if palindromeCnt(words[i],words[j]):
                    ret.append([i,j])
                if palindromeCnt(words[j],words[i]):
                    ret.append([j,i])
        return ret

