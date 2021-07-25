import { Category } from "./category"

export class Quiz {
    qid: number
    title: string
    description: string
    maxMarks: string
    numberOfQuestions: string
    active: boolean = false
    category: Category
    categoryId: number
}
