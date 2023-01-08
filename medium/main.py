print("Welcome! Here are my python solutions to various leetcode problems")

# Leetcode 739 - Daily Temperatures

class Solution739(object):
    def dailyTemperatures(self, temperatures):
        """
        :type temperatures: List[int]
        :rtype: List[int]
        """
        if len(temperatures) <= 1:
            return [0]
        stack = [len(temperatures) - 1]
        ans = [0]
        for pos in range(len(temperatures) - 2, -1, -1):
            while len(stack) > 0 and temperatures[stack[-1]] <= temperatures[pos]:
                stack.pop()
            if len(stack) == 0:
                ans.append(0)
            else:
                ans.append(stack[-1] - pos)
            stack.append(pos)
        ans.reverse()
        return ans


# Leetcode 2497 - Maximum Star Sum of a Graph

class Solution2497(object):
    def maxStarSum(self, vals, edges, k):
        graph = {}
        maximum = 0
        temp = 0
        curr = []
        iterate = 0
        for i in edges:
            if i[0] in graph:
                graph[i[0]].append(vals[i[1]])
            else:
                graph[i[0]] = [vals[i[1]]]
            if i[1] in graph:
                graph[i[1]].append(vals[i[0]])
            else:
                graph[i[1]] = [vals[i[0]]]

        for i in graph:
            graph[i].sort()
            for j in range(len(graph[i]) - 1, -1, -1):
                if iterate == k or graph[i][j] <= 0:
                    break
                temp += graph[i][j]
                iterate += 1
            maximum = max(maximum, temp + vals[i])
            temp = 0
            iterate = 0

        if maximum == 0:
            return max(vals)
        else:
            return maximum


# Leetcode 2512 - Reward Top K Students

class Solution2512:
    def topStudents(self, positive_feedback, negative_feedback, report,
                    student_id, k):

        p = set(positive_feedback)
        s = set(negative_feedback)

        students_left = k
        current_scores = []
        top_k_students = []
        student_ranks = [0] * len(report)
        words = []
        id_map = {}
        for i in range(len(report)):
            words = report[i].split()
            for j in words:
                if j in p:
                    student_ranks[i] += 3
                elif j in s:
                    student_ranks[i] -= 1

        for i in range(len(student_ranks)):
            id_map[student_id[i]] = student_ranks[i]

        student_ranks = list(dict.fromkeys(student_ranks))
        student_ranks.sort()

        # UP TO HERE CODE IS CLEAN
        for i in range(len(student_ranks) - 1, len(student_ranks) - k - 1, -1):
            current_scores = []
            for j in id_map:
                if id_map[j] == student_ranks[i]:
                    current_scores.append(j)

            current_scores.sort()
            if len(current_scores) >= students_left:
                for i in range(students_left):
                    top_k_students.append(current_scores[i])
                return top_k_students
            else:
                for i in current_scores:
                    top_k_students.append(i)
            students_left -= len(current_scores)

        return top_k_students


# Leetcode 692 - Top K Frequent Words

class Solution692:
    def topKFrequent(self, words, k):
        top_k_words = []
        frequency = {}
        sorted_list = []
        same_frequency = {}
        for i in words:
            if i in frequency:
                frequency[i] += 1
            else:
                frequency[i] = 1
        sorted_map = dict(sorted(frequency.items(), key=lambda item: item[1], reverse=True))
        words_left = k
        for i in sorted_map:
            if sorted_map[i] in same_frequency:
                same_frequency[sorted_map[i]].append(i)
            else:
                same_frequency[sorted_map[i]] = [i]

        for i in same_frequency:
            same_frequency[i] = sorted(same_frequency[i])
            if len(same_frequency[i]) >= words_left:
                for j in range(words_left):
                    top_k_words.append(same_frequency[i][j])
                return top_k_words
            else:
                for j in same_frequency[i]:
                    top_k_words.append(j)
            words_left -= len(same_frequency[i])
        return top_k_words
